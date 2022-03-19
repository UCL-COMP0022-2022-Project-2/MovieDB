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

    //Hashmap<String, List<Double>> example:  <Drama, [1, 2.35, 4, 1, 6, 4]>
    // (openness, agreeableness, emotional_stability, conscientiousness, extraversion, the num of users)

    //read all personality and process, store in hashmap
    //output hashmap to database
    @Override
    public void initGenrePersonality(){
        HashMap<String,double[]> genreMap = new HashMap<>();

        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT p.userId,r.movieId,m.genres,r.rating,p.openness, p.agreeableness, p. emotional_stability, p.conscientiousness,p.extraversion " +
                    "FROM personality_rating r LEFT OUTER JOIN  personality p  ON p.userId = r.userId " +
                    "LEFT OUTER JOIN movies m ON m.movieId = r.movieId " +
                    "WHERE m.genres IS NOT NULL";
            //join user personality and the rating they give, and gain the genres of the movie

            List<Integer> param = new ArrayList<>();
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                //get genres and personality
                String genres = rs.getString("genres");

                double openness = rs.getDouble("openness");
                double agreeableness = rs.getDouble("agreeableness");
                double emotional_stability = rs.getDouble("emotional_stability");
                double conscientiousness = rs.getDouble("conscientiousness");
                double extraversion = rs.getDouble("extraversion");

                double rating = rs.getDouble("rating");

//                                System.out.println(genres);


/*              Explain the algorithm:
                Domain of value:
                rating: 0.5-5    midpoint:2.75
                personality (e.g. openness): 1-7    midpoint: 4

                First calculate the weight
                Rating:5 -> weight: 1;  it will positively affect the parameter (e.g. openness + 7)
                Rating:1 -> weight: -1;  it will negatively affect the parameter (e.g. openness + 1)
                Rating:3 -> weight: 0;  Not affect the parameter
                The positive and negative is compare to its midpoint so there is some step to remap the change of
                score to the right domain(not cross the mid-point)
                 (e.g. make sure openness 7 will only positively affect the score which the result will not be under 4)
                */


                //calculate the weight of the user
                double weight =(rating - 2.75) / 2.25;

                //for every genre
                String[] subSentences = genres.split("\\|");
                for (String sub : subSentences) {
//                    System.out.println("sub\n"+ sub);

                    //create new genre if there is no such genre
                    if(!genreMap.containsKey(sub)){
                        double[] list = {0, 0, 0, 0, 0, 0};
                        genreMap.put(sub,list);
                    }
                    double[] list = genreMap.get(sub);

                    //remap (-1 ~ 1)*personality to 1-7
                    if(weight > 0){
                        list[0] += (openness - 3) * weight + 3;     //(openness - 3) + 3 means make the value not cross the mid-point
                        list[1] += (agreeableness - 3) * weight + 3;
                        list[2] += (emotional_stability - 3) * weight + 3;
                        list[3] += (conscientiousness - 3) * weight + 3;
                        list[4] += (extraversion - 3) * weight + 3;
                        list[5] += 1;
//                        System.out.println("weight > 0\n"+list[0]+"\n"+list[5]);
                        genreMap.put(sub,list);

                    }
                    else if(weight < 0){
                        list[0] += 7 + (openness - 3) * weight + 3;
                        list[1] += 7 + (agreeableness - 3) * weight + 3;
                        list[2] += 7 + (emotional_stability - 3) * weight + 3;
                        list[3] += 7 + (conscientiousness - 3) * weight + 3;
                        list[4] += 7 + (extraversion - 3) * weight + 3;
                        list[5] += 1;
//                        System.out.println("weight < 0"+list);
                        genreMap.put(sub,list);

                    }
                    //weight = 0 ignored (not affect the parameter)
                }
            }



            //write into database

            //for every genre in hashmap
            for (String key : genreMap.keySet()) {
                double[] list = genreMap.get(key);
                List<Double> temp_param = new ArrayList<>();

                //taking average
                for(int i = 0; i < 5; i++){
                    temp_param.add(list[i] / list[5]);
                }
                //clear existing data
                String deleteSql = "delete from tag_personality where true";
                MySQLHelper.executeUpdate(conn, deleteSql, null);
                //insert if not exist
                sql = "\n" +
                        "INSERT INTO genre_personality(genre, openness, agreeableness, emotional_stability, conscientiousness, extraversion)  SELECT \""+key+"\",?,?,?,?,? "+
                        "WHERE NOT EXISTS(SELECT genre FROM genre_personality WHERE genre =  \""+key+"\")";

                // Executing queries...
                int line = MySQLHelper.executeUpdate(conn, sql, temp_param);
//                System.out.println(line);

            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.printf(String.valueOf(genreMap));

    }

    @Override
    public HashMap<String, Personality> getMoviePersonality(List<String> tags) {
        HashMap<String,double[]> genreMap = new HashMap<>();
        HashMap<String, Personality> result = new HashMap<>(tags.size());
        //result: (openness, agreeableness, emotional_stability, conscientiousness, extraversion, the num of genres)

        try {

//                System.out.println("genres:\n" +genres);

                //read the genres for this movie
                Connection conn = MySQLHelper.getConnection();
                for (String sub : tags) {
//                    System.out.println(sub);


                    String sql = "SELECT * FROM genre_personality WHERE genre = ?;";
                    List<String> param_genre = new ArrayList<>();
                    param_genre.add(sub);

                    // Executing queries...
                    ResultSet rs_genre = MySQLHelper.executingQuery(conn, sql, param_genre);
//                    System.out.println(param_genre);
                    while(rs_genre.next()) {
                        Personality personality = new Personality();
                        //get score and put seperated tag result in returning list
                        double openness = rs_genre.getDouble("openness");
                        double agreeableness = rs_genre.getDouble("agreeableness");
                        double emotional_stability = rs_genre.getDouble("emotional_stability");
                        double conscientiousness = rs_genre.getDouble("conscientiousness");
                        double extraversion = rs_genre.getDouble("extraversion");


                        personality.setOpenness(openness);
                        personality.setAgreeableness(agreeableness);
                        personality.setEmotional_stability(emotional_stability );
                        personality.setConscientiousness(conscientiousness);
                        personality.setExtraversion(extraversion);
                        //put single tag output
                        result.put(sub, personality);
                        double[] list = {openness, agreeableness, emotional_stability, conscientiousness, extraversion};
                        genreMap.put(sub,list);

                    }
                }
            MySQLHelper.closeConnection(conn);

            double[] tempResult = {0, 0, 0, 0, 0};
            //sum of tags
            StringBuilder sb = new StringBuilder();


            int num_genre = tags.size();
//            System.out.println(tags.size());
            for (String sub : tags) {

                double[] list = genreMap.get(sub);

                //add genre data to result
                for(int i = 0; i < 5; i++){
                    tempResult[i] += list[i];
//                        System.out.println("result "+ i +" "+ tempResult[i] );
                }
                sb.append(sub+",");

            }
            //delete the last comma
            sb.delete(sb.length()-1,sb.length());



            //taking average
            for(int i = 0; i < 5; i++){
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
//        new ucl.comp0022.team2.dao.impl.PersonalityDaoImpl().initGenrePersonality();
        ArrayList<String> test = new ArrayList<String>();
        test.add("Adventure");
        test.add("Action");
        System.out.println(new PersonalityByGenresDaoImpl().getMoviePersonality(test));
    }


}


