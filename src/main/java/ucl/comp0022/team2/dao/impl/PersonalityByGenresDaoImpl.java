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
    public void initGenrePersonality() {
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

//                System.out.println("genres:\n" +genres);

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
            MySQLHelper.closeConnection(conn);

            double[] tempResult = {0, 0, 0, 0, 0};
            //sum of genres
            StringBuilder sb = new StringBuilder();


            int num_genre = genres.size();
//            System.out.println(genres.size());
            for (String sub : genres) {

                double[] list = genreMap.get(sub);

                //add genre data to result
                for (int i = 0; i < 5; i++) {
                    tempResult[i] += list[i];
//                        System.out.println("result "+ i +" "+ tempResult[i] );
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

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    public static void main(String[] args) {
        new ucl.comp0022.team2.dao.impl.PersonalityByGenresDaoImpl().initGenrePersonality();
//        ArrayList<String> test = new ArrayList<String>();
//        test.add("Adventure");
//        test.add("Action");
//        System.out.println(new PersonalityByGenresDaoImpl().getMoviePersonality(test));
    }


}


