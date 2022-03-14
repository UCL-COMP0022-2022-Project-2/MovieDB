package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PopularDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PopularDaoImpl implements PopularDao {

    @Override
    public List<Movie> getPopularMovieAvgList() {
        List<Movie> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();
            // Writing sql and parameters...
            String sql = "SELECT m.movieID as movieID,m.title as title,m.genres as genres,"+
            "m.year as year,sc.score as score, sc.avg_rating as avg_rating\n"+
            "FROM movies AS m, ratings AS r\n" +
            "(SELECT var(rating) as score, movieID\n"+
            "FROM ratings\n"+
            "GROUP BY movieID) as sc\n"+
            "WHERE sc.movieID = m.movieID\n"+
            "and m.movieID = r.movieID\n"+
            "ORDER BY score DESC;";
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                Double score = rs.getDouble("score");
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                Double rating = rs.getDouble("rating");
                int year = rs.getInt("year");
                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movie.setRating(rating);
                list.add(movie);
            }
            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<HashMap<Integer, Double>> getPopularMovieTagsList() {
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT COUNT(t.movieId) AS score, m.movieId FROM movies m\n" +
                    "LEFT JOIN tags AS t ON m.movieId = t.movieId\n" +
                    "GROUP BY m.movieId ORDER BY score DESC;";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            while(rs.next()) {
                HashMap<Integer, Double> map = new HashMap<>();
                Integer movieId = rs.getInt("movieId");
                Double score = rs.getDouble("score");
                map.put(movieId,score);
                list.add(map);
            }
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<HashMap<Integer, Double>> getPopularMovieRatingsList() {
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT COUNT(r.movieId) AS score, m.movieId FROM movies m\n" +
                    "LEFT JOIN ratings AS r ON m.movieId = r.movieId\n" +
                    "GROUP BY m.movieId ORDER BY score DESC;";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            while(rs.next()) {
                HashMap<Integer, Double> map = new HashMap<>();
                Integer movieId = rs.getInt("movieId");
                Double score = rs.getDouble("score");
                map.put(movieId,score);
                list.add(map);
            }
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(new PopularDaoImpl().getPopularMovieAvgList());
    }
}