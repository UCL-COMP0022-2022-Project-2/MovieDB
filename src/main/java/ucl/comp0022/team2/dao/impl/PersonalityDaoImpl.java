package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PersonalityDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class PersonalityDaoImpl implements PersonalityDao {
    HashMap<String, double[]> genreMap = new HashMap<>();

    //Hashmap<String, List<Double>> example:  <Drama, [1, 2.35, 4, 1, 6, 4]>
    // (openness, agreeableness, emotional_stability, conscientiousness, extraversion, the num of users)

    //read all personality and process, store in hashmap
    public void initGenrePersonality(){
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT p.userId,r.movie_id,m.genres,r.rating,p.openness, p.agreeableness, p. emotional_stability, p.conscientiousness,p.extraversion " +
                    "FROM personality_rating r LEFT OUTER JOIN  personality p  ON p.userId = r.userId " +
                    "LEFT OUTER JOIN movies m ON m.movieId = r.movie_id " +
                    "WHERE r.movie_id = 1 ";
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

                //calculate the weight of the user
                //e.g. Rating:5 -> weight: 1;  it will positively affect the parameter (openness + 7)
                //Rating:1 -> weight: -1;  it will positively affect the parameter (openness + 1)
                //Rating:3 -> weight: 0;  Not affect the parameter
                double weight =(rating - 3) / 2;

                //for every genre
                String[] subSentences = genres.split("|");
                for (String sub : subSentences) {
                    //create new genre
                    if(!genreMap.containsKey(sub)){
                        double[] list = {0, 0, 0, 0, 0, 0};
                        genreMap.put(sub,list);
                    }
                    double[] list = genreMap.get(sub);

                    //remap (-1 ~ 1)*personality to 1-7
                    if(weight > 0){
                        list[0] += openness * weight;
                        list[1] += agreeableness * weight;
                        list[2] += emotional_stability * weight;
                        list[3] += conscientiousness * weight;
                        list[4] += extraversion * weight;
                        list[5] += 1;
                        System.out.println("weight > 0"+list);
                        genreMap.put(sub,list);

                    }
                    else if(weight < 0){
                        list[0] += 7 + openness * weight;
                        list[1] += 7 + agreeableness * weight;
                        list[2] += 7 + emotional_stability * weight;
                        list[3] += 7 + conscientiousness * weight;
                        list[4] += 7 + extraversion * weight;
                        list[5] += 1;
                        System.out.println("weight < 0"+list);
                        genreMap.put(sub,list);

                    }
                    //weight = 0 ignored (not affect the parameter)
                }
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Double> getMoviePersonality(int movieIdParam) {


        Movie movie = new Movie();
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
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public static void main(String[] args) {
        new ucl.comp0022.team2.dao.impl.PersonalityDaoImpl().initGenrePersonality();
//        System.out.println(new ucl.comp0022.team2.dao.impl.SearchingReportDaoImpl().getAverageScore(1));
//        System.out.println(new ucl.comp0022.team2.dao.impl.PersonalityDaoImpl().getMoviePersonality(1));
    }


}


