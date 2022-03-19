package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PersonalityByGenresDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Personality;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class PersonalityByGenresDaoImpl implements PersonalityByGenresDao {

    @Override
    public void initialize() {
        HashMap<String, double[]> genreMap = new HashMap<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();
            //clear existing data
            String deleteSql = "delete from genre_personality where true";
            MySQLHelper.executeUpdate(conn, deleteSql, null);
            // Writing sql and parameters...
            String sql = "SELECT p.userId,r.movieId,m.genres,r.rating,p.openness, p.agreeableness, p. emotional_stability, p.conscientiousness,p.extraversion " +
                    "FROM personality_rating r LEFT OUTER JOIN  personality p  ON p.userId = r.userId " +
                    "LEFT OUTER JOIN movies m ON m.movieId = r.movieId " +
                    "WHERE m.genres IS NOT NULL";
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while (rs.next()) {
                //get genres and personality
                String genres = rs.getString("genres");
                double openness = rs.getDouble("openness");
                double agreeableness = rs.getDouble("agreeableness");
                double emotional_stability = rs.getDouble("emotional_stability");
                double conscientiousness = rs.getDouble("conscientiousness");
                double extraversion = rs.getDouble("extraversion");
                double rating = rs.getDouble("rating");
                //for every genre
                String[] subSentences = genres.split("\\|");
                for (String sub : subSentences) {
                    //create new genre if there is no such genre
                    if (!genreMap.containsKey(sub)) {
                        double[] list = {0, 0, 0, 0, 0, 0};
                        genreMap.put(sub, list);
                    }
                    double[] list = genreMap.get(sub);
                    list[0] += (rating - 2.75) * (openness - 4);
                    list[1] += (rating - 2.75) * (agreeableness - 4);
                    list[2] += (rating - 2.75) * (emotional_stability - 4);
                    list[3] += (rating - 2.75) * (conscientiousness - 4);
                    list[4] += (rating - 2.75) * (extraversion - 4);
                    list[5] += 1;
                    genreMap.put(sub, list);
                }
            }

            //write into database
            //for every genre in hashMap
            for (String key : genreMap.keySet()) {
                double[] list = genreMap.get(key);
                List<Object> temp_param = new ArrayList<>();
                temp_param.add(key);
                //taking average
                for (int i = 0; i < 5; i++) {
                    temp_param.add(list[i] / list[5]);
                }
                //insert if not exist
                sql = "insert into genre_personality values(?,?,?,?,?,?)";
                // Executing queries...
                MySQLHelper.executeUpdate(conn, sql, temp_param);
            }
            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public HashMap<String, Personality> getMoviePersonality(List<String> genres) {
        HashMap<String, double[]> genreMap = new HashMap<>();
        HashMap<String, Personality> result = new HashMap<>(genres.size());
        //result: (openness, agreeableness, emotional_stability, conscientiousness, extraversion, the num of genres)
        try {
            //read the genres for this movie
            Connection conn = MySQLHelper.getConnection();
            for (String sub : genres) {
//                    System.out.println(sub);


                String sql = "SELECT * FROM genre_personality WHERE genre = ?;";
                List<String> param_genre = new ArrayList<>();
                param_genre.add(sub);

                // Executing queries...
                ResultSet rs_genre = MySQLHelper.executingQuery(conn, sql, param_genre);
//                    System.out.println(param_genre);
                while (rs_genre.next()) {
                    Personality personality = new Personality();
                    //get score and put seperated tag result in returning list
                    double openness = rs_genre.getDouble("openness");
                    double agreeableness = rs_genre.getDouble("agreeableness");
                    double emotional_stability = rs_genre.getDouble("emotional_stability");
                    double conscientiousness = rs_genre.getDouble("conscientiousness");
                    double extraversion = rs_genre.getDouble("extraversion");


                    personality.setOpenness(openness);
                    personality.setAgreeableness(agreeableness);
                    personality.setEmotional_stability(emotional_stability);
                    personality.setConscientiousness(conscientiousness);
                    personality.setExtraversion(extraversion);
                    //put single tag output
                    result.put(sub, personality);
                    double[] list = {openness, agreeableness, emotional_stability, conscientiousness, extraversion};
                    genreMap.put(sub, list);

                }
            }
            double[] tempResult = {0, 0, 0, 0, 0};
            //sum of genres
            StringBuilder sb = new StringBuilder();
            int num_genre = genres.size();
            for (String sub : genres) {
                double[] list = genreMap.get(sub);
                //add genre data to result
                for (int i = 0; i < 5; i++) {
                    tempResult[i] += list[i];
                }
                sb.append(sub).append(",");
            }
            //delete the last comma
            sb.delete(sb.length() - 1, sb.length());
            //taking average
            for (int i = 0; i < 5; i++) {
                tempResult[i] = tempResult[i] / num_genre;
            }
            Personality personality = new Personality();
            personality.setOpenness(tempResult[0]);
            personality.setAgreeableness(tempResult[1]);
            personality.setEmotional_stability(tempResult[2]);
            personality.setConscientiousness(tempResult[3]);
            personality.setExtraversion(tempResult[4]);
            result.put(sb.toString(), personality);
            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Personality getAllGenresAveragePersonality() {
        Connection connection = null;
        Personality personality = new Personality();
        try {
            connection = MySQLHelper.getConnection();
            String sql = "select avg(openness) as avg_openness,avg(agreeableness) as avg_agreeableness,avg(emotional_stability) as avg_emotional_stability,\n" +
                    "       avg(conscientiousness) as avg_conscientiousness,avg(extraversion) as avg_extraversion\n" +
                    "from genre_personality";
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
    public List<String> getAllGenres() {
        Connection connection = null;
        List<String> result = new ArrayList<>();
        try {
            connection = MySQLHelper.getConnection();
            String sql = "select genre from genre_personality where genre != 'NULL'";
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
//        System.out.println(new PersonalityByGenresDaoImpl().getAllGenres());
//        System.out.println(new PersonalityByGenresDaoImpl().getAllGenresAveragePersonality());
//        ArrayList<String> test = new ArrayList<String>();
//        test.add("Adventure");
//        test.add("Action");
//        System.out.println(new PersonalityByGenresDaoImpl().getMoviePersonality(test));
    }


}


