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
    public List<Movie> getPopularMovieAvgList(String limit) {
        List<Movie> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();
            // Writing sql and parameters...
            String sql = "SELECT IFNULL(AVG(r.rating), 0) AS avg_rating, m.movieId, m.title,m.genres,m.year\n"+
                    "FROM movies m\n" +
                    "LEFT JOIN ratings AS r ON m.movieId = r.movieId\n" +
                    "GROUP BY m.movieId ORDER BY avg_rating DESC\n" +
                    "LIMIT " + limit + ";";
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                double rating = Double.parseDouble(new DecimalFormat("######0.0").format(rs.getDouble("avg_rating")));
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
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
    public List<Movie> getPopularMovieTagsList(String limit) {
        List<Movie> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT COUNT(t.movieId) AS score, m.movieId, m.title,m.genres,m.year ,IFNULL(AVG(r.rating),0) AS  avg_rating\n" +
                    "FROM movies m, ratings r, tags t\n"+
                    "where m.movieId = t.movieId\n" +
                    "and m.movieId = r.movieid\n"+
                    "GROUP BY m.movieId ORDER BY score DESC\n"+
                    "LIMIT "+ limit +";";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            while(rs.next()) {
                Movie movie = new Movie();
                double rating = Double.parseDouble(new DecimalFormat("######0.0").format(rs.getDouble("avg_rating")));
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");
                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movie.setRating(rating);
                list.add(movie);
            }
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Movie> getPopularMovieRatingsList(String limit) {
        List<Movie> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT COUNT(r.movieId) AS score, m.movieId, m.title,m.genres,m.year,IFNULL(AVG(r.rating),0) AS  avg_rating\n" +
                    "FROM movies m\n" +
                    "LEFT JOIN ratings AS r ON m.movieId = r.movieId\n" +
                    "GROUP BY m.movieId ORDER BY score DESC\n"+
                    "LIMIT "+ limit +";";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            while(rs.next()) {
                Movie movie = new Movie();
                double rating = Double.parseDouble(new DecimalFormat("######0.0").format(rs.getDouble("avg_rating")));
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");
                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movie.setRating(rating);
                list.add(movie);
            }
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(new PopularDaoImpl().getPopularMovieTagsList("0, 50"));
    }
}