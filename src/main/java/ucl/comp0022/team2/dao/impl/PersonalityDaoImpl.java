package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PersonalityDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Repository
public class PersonalityDaoImpl implements PersonalityDao {

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
            String sql = "SELECT p.userId,r.movie_id,m.genres,r.rating,p.openness, p.agreeableness, p. emotional_stability, p.conscientiousness,p.extraversion " +
                    "FROM personality_rating r LEFT OUTER JOIN  personality p  ON p.userId = r.userId " +
                    "LEFT OUTER JOIN movies m ON m.movieId = r.movie_id " +
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
                rating: 1-5    midpoint:3
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
                double weight =(rating - 3) / 2;

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
    public double[] getMoviePersonality(int movieIdParam) {
        HashMap<String,double[]> genreMap = new HashMap<>();
        //result: (openness, agreeableness, emotional_stability, conscientiousness, extraversion, the num of genres)
        double[] result = {0, 0, 0, 0, 0};

        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT * FROM movies WHERE movieId = ?;";
            List<Integer> param = new ArrayList<>();
            param.add(movieIdParam);

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);

            // Reading, analysing and saving the results...
            while(rs.next()) {
                String genres = rs.getString("genres");


//                System.out.println("genres:\n" +genres);

                //read the genres for this movie
                String[] subSentences = genres.split("\\|");

                for (String sub : subSentences) {
//                    System.out.println(sub);
                    sql = "SELECT * FROM genre_personality WHERE genre = ?;";
                    List<String> param_genre = new ArrayList<>();
                    param_genre.add(sub);

                    // Executing queries...
                    ResultSet rs_genre = MySQLHelper.executingQuery(conn, sql, param_genre);
//                    System.out.println(param_genre);
                    while(rs_genre.next()) {

                        //get score
                        double openness = rs_genre.getDouble("openness");
                        double agreeableness = rs_genre.getDouble("agreeableness");
                        double emotional_stability = rs_genre.getDouble("emotional_stability");
                        double conscientiousness = rs_genre.getDouble("conscientiousness");
                        double extraversion = rs_genre.getDouble("extraversion");
                        double[] list = {openness, agreeableness, emotional_stability, conscientiousness, extraversion};
                        genreMap.put(sub,list);

                    }
                }




                int num_genre = 0;
                //get data of the genres about the movie
                for (String sub : subSentences) {

                    double[] list = genreMap.get(sub);

//                    System.out.println("sub\n"+ sub);

                    //add genre data to result
                    for(int i = 0; i < list.length; i++){
                        result[i] += list[i];
//                        System.out.println("result "+ i +" "+ result[i] );
                    }
                    num_genre += 1;
                }

                //taking average
                for(int i = 0; i < result.length; i++){
                    result[i] = result[i] / num_genre;
                }
            }


            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    public static void main(String[] args) {
//        new ucl.comp0022.team2.dao.impl.PersonalityDaoImpl().initGenrePersonality();
        System.out.println(Arrays.toString(new PersonalityDaoImpl().getMoviePersonality(1)));
    }


}


