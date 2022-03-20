package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PersonalityByTagsDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Personality;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

@Repository
public class PersonalityByTagsDaoImpl implements PersonalityByTagsDao {
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
                    "(select pr.movieId, p.openness, p.agreeableness, p.emotional_stability, p.conscientiousness, p.extraversion,  pr.rating\n" +
                    "from personality_rating pr join personality p on pr.userId = p.userId\n" +
                    "order by movieId) as p_pr\n" +
                    "on new_tags.movieId = p_pr.movieId\n";
            ResultSet resultSet = MySQLHelper.executingQuery(conn, calculateSql, null);
            Map<String, Personality> hashMap = new HashMap<>();
            while (resultSet.next()) {
                String tags = resultSet.getString("tags");
                double openness = resultSet.getDouble("openness");
                double agreeableness = resultSet.getDouble("agreeableness");
                double emotional_stability = resultSet.getDouble("emotional_stability");
                double conscientiousness = resultSet.getDouble("conscientiousness");
                double extraversion = resultSet.getDouble("extraversion");
                double rating = resultSet.getDouble("rating");
                String[] animalsArray = tags.split(",");
                for (String tag : animalsArray) {
                    tag = tag.toUpperCase();
                    if (!hashMap.containsKey(tag)) {
                        hashMap.put(tag, new Personality(0, 0, 0, 0, 0));
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
            for (Map.Entry<String, Personality> entry : hashMap.entrySet()) {
                List<Object> curList = new ArrayList<>(6);
                curList.add(entry.getKey());
                Personality curPersonality = entry.getValue();
                int curPersonalityCount = curPersonality.getCount();
                curList.add(curPersonality.getOpenness() / curPersonalityCount);
                curList.add(curPersonality.getAgreeableness() / curPersonalityCount);
                curList.add(curPersonality.getEmotional_stability() / curPersonalityCount);
                curList.add(curPersonality.getConscientiousness() / curPersonalityCount);
                curList.add(curPersonality.getExtraversion() / curPersonalityCount);
                resultList.add(curList);
            }
            int rowsAffected = MySQLHelper.executeMultipleRowUpdate(conn, insertSql, resultList);
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(conn);
        }
        return false;
    }

    @Override
    public HashMap<String, Personality> getPersonalitiesByTags(List<String> tags) {
        int count = tags.size();
        Connection connection = null;
        HashMap<String, Personality> map = new HashMap<>(count);
        //an array to store the sum of personalities
        double[] sumPersonality = new double[]{0, 0, 0, 0, 0};
        try {
            connection = MySQLHelper.getConnection();
            for (String tag : tags) {
                tag = tag.trim();
                Personality personality = new Personality();
                String sql = "select openness, agreeableness, emotional_stability, conscientiousness, extraversion from tag_personality where tag = ?";
                ResultSet resultSet = MySQLHelper.executingQuery(connection, sql, Collections.singletonList(tag));
                if (resultSet.next()) {
                    double currentOpenness = resultSet.getDouble("openness");
                    personality.setOpenness(currentOpenness);
                    sumPersonality[0] += currentOpenness;

                    double currentAgreeableness = resultSet.getDouble("agreeableness");
                    personality.setAgreeableness(currentAgreeableness);
                    sumPersonality[1] += currentAgreeableness;

                    double currentEmotional_stability = resultSet.getDouble("emotional_stability");
                    personality.setEmotional_stability(currentEmotional_stability);
                    sumPersonality[2] += currentEmotional_stability;

                    double currentConscientiousness = resultSet.getDouble("conscientiousness");
                    personality.setConscientiousness(currentConscientiousness);
                    sumPersonality[3] += currentConscientiousness;

                    double currentExtraversion = resultSet.getDouble("extraversion");
                    personality.setExtraversion(currentExtraversion);
                    sumPersonality[4] += currentExtraversion;

                    map.put(tag, personality);
                }
                //create a new personality to store the average value of  tags' personalities
                Personality averagePersonality = new Personality();
                double openness = sumPersonality[0]/count;
                averagePersonality.setOpenness(Math.round(openness*100)/100.0);
                double agreeableness = sumPersonality[1]/count;
                averagePersonality.setAgreeableness(Math.round(agreeableness*100)/100.0);
                double emotional_stability = sumPersonality[2]/count;
                averagePersonality.setEmotional_stability(Math.round(emotional_stability*100)/100.0);
                double conscientiousness = sumPersonality[3]/count;
                averagePersonality.setConscientiousness(Math.round(conscientiousness*100)/100.0);
                double extraversion = sumPersonality[4]/count;
                averagePersonality.setExtraversion(Math.round(extraversion*100)/100.0);
                map.put("theTotalAverage", averagePersonality);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(connection);
        }
        return map;
    }

    @Override
    public Personality getAllTagsAveragePersonality() {
        Connection connection = null;
        Personality personality = new Personality();
        try {
            connection = MySQLHelper.getConnection();
            String sql = "select avg(openness) as avg_openness,avg(agreeableness) as avg_agreeableness,avg(emotional_stability) as avg_emotional_stability,\n" +
                    "       avg(conscientiousness) as avg_conscientiousness,avg(extraversion) as avg_extraversion\n" +
                    "from tag_personality";
            ResultSet resultSet = MySQLHelper.executingQuery(connection, sql, null);
            if (resultSet.next()) {
                double openness = resultSet.getDouble("avg_openness");
                personality.setOpenness(Math.round(openness*100)/100.0);
                double agreeableness = resultSet.getDouble("avg_agreeableness");
                personality.setAgreeableness(Math.round(agreeableness*100)/100.0);
                double emotional_stability = resultSet.getDouble("avg_emotional_stability");
                personality.setEmotional_stability(Math.round(emotional_stability*100)/100.0);
                double conscientiousness = resultSet.getDouble("avg_conscientiousness");
                personality.setConscientiousness(Math.round(conscientiousness*100)/100.0);
                double extraversion = resultSet.getDouble("avg_extraversion");
                personality.setExtraversion(Math.round(extraversion*100)/100.0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(connection);
        }
        return personality;
    }

    @Override
    public List<String> getTagsByInitialLetter(char capital) {
        Connection connection = null;
        List<String> result = new ArrayList<>();
        try {
            connection = MySQLHelper.getConnection();
            String sql = "";
            if (capital == '#') {
                sql = "select tag from tag_personality where tag not regexp '^[A-Z].';";
            } else {
                sql = "select tag from tag_personality where tag like '" + capital + "%'";
            }
            ResultSet resultSet = MySQLHelper.executingQuery(connection, sql, null);
            while (resultSet.next()) {
                String tag = resultSet.getString(1);
                result.add(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(connection);
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new PersonalityByTagsDaoImpl().initialize());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("QUIRKY");
        arrayList.add("QUOTABLE");
        System.out.println(new PersonalityByTagsDaoImpl().getPersonalitiesByTags(arrayList));
    }
}
