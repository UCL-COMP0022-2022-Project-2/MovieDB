package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PersonalityByTagsDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Personality;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalityByTagsImpl implements PersonalityByTagsDao {
    @Override
    public boolean initialize() {
        Connection conn = null;
        try {
            conn = MySQLHelper.getConnection();
            //clear existing data
            String deleteSql = "delete from tag_personality where true";
            MySQLHelper.executeUpdate(conn, deleteSql, null);
            //calculate the personality of each tag and insert them into a hashMap.
            String calculateSql = "select new_tags.tags, p_pr.openness, p_pr.agreeableness, p_pr.emotional_stability, p_pr.conscientiousness, p_pr.extraversion, p_pr.rating\n" +
                    "from\n" +
                    "(select movieId, GROUP_CONCAT(DISTINCT tag) as tags\n" +
                    "from tags\n" +
                    "group by movieId) as new_tags\n" +
                    "join\n" +
                    "(select pr.movie_id, p.openness, p.agreeableness, p.emotional_stability, p.conscientiousness, p.extraversion,  pr.rating\n" +
                    "from personality_rating pr join personality p on pr.userId = p.userId\n" +
                    "order by movie_id) as p_pr\n" +
                    "on new_tags.movieId = p_pr.movie_id\n" +
                    "order by movie_id\n";
            ResultSet resultSet = MySQLHelper.executingQuery(conn,calculateSql,null);
            Map<String, Personality> hashMap = new HashMap<>();
            while(resultSet.next()) {
                String tags = resultSet.getString("tags");
                double openness = resultSet.getDouble("openness");
                double agreeableness = resultSet.getDouble("agreeableness");
                double emotional_stability = resultSet.getDouble("emotional_stability");
                double conscientiousness = resultSet.getDouble("conscientiousness");
                double extraversion = resultSet.getDouble("extraversion");
                double rating = resultSet.getDouble("rating");
                String[] animalsArray = tags.split(",");
                for(String tag: animalsArray){
                    tag = tag.toUpperCase();
                    if(!hashMap.containsKey(tag)){
                        hashMap.put(tag, new Personality(0,0,0,0,0));
                    }
                    Personality curPersonality = hashMap.get(tag);
                    curPersonality.addOpenness((rating - 2.75) * (openness - 4));
                    curPersonality.addAgreeAbleness((rating - 2.75) * (agreeableness - 4));
                    curPersonality.addEmotionalStability((rating - 2.75) * (emotional_stability - 4));
                    curPersonality.addConscientiousness((rating - 2.75) * (conscientiousness - 4));
                    curPersonality.addExtraversion((rating - 2.75) * (extraversion - 4));
                    curPersonality.countAddOne();
                }
            }
            //after the HashMap has been constructed, insert the values into the table.
            String insertSql = "insert into tag_personality values(?,?,?,?,?,?)";
            List<List<Object>> resultList = new ArrayList<>();
            for(Map.Entry<String, Personality> entry: hashMap.entrySet()){
                List<Object> curList = new ArrayList<>(6);
                curList.add(entry.getKey());
                Personality curPersonality = entry.getValue();
                int curPersonalityCount = curPersonality.getCount();
                curList.add(curPersonality.getOpenness()/curPersonalityCount);
                curList.add(curPersonality.getAgreeableness()/curPersonalityCount);
                curList.add(curPersonality.getEmotional_stability()/curPersonalityCount);
                curList.add(curPersonality.getConscientiousness()/curPersonalityCount);
                curList.add(curPersonality.getExtraversion()/curPersonalityCount);
                resultList.add(curList);
            }
            int rowsAffected =  MySQLHelper.executeMultipleRowUpdate(conn, insertSql, resultList);
            if(rowsAffected > 0){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(conn);
        }
        return false;
    }

    @Override
    public HashMap<String, Personality> getPersonalitiesByTags(String[] tags) {
        Connection connection = null;
        for(String tag: tags){
            String sql = "";
        }
        return null;
    }

    @Override
    public List<String> getTagsByInitialLetter(char capital) {
        Connection connection = null;
        List<String> result = new ArrayList<>();
        try{
            connection = MySQLHelper.getConnection();
            String sql = "";
            if(capital == '#'){
                sql = "select tag from tag_personality where tag not regexp '^[A-Z].';";
            }else {
                sql = "select tag from tag_personality where tag like '"+ capital + "%'";
            }
            ResultSet resultSet = MySQLHelper.executingQuery(connection, sql, null);
            while (resultSet.next()){
                String tag = resultSet.getString(1);
                result.add(tag);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(connection);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PersonalityByTagsImpl().initialize());
//        System.out.println(new PersonalityByTagsImpl().getTagsByInitialLetter('a'));
    }
}
