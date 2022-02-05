package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.dao.interfaces.MovieInfoDao;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieInfoDaoImpl implements MovieInfoDao {

    @Override
    public Movie getMovieInfoByMovieId(int movieIdParam) {

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
                movie.setRating(getMovieRatingByMovieId(movieId));
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movie;
    }

    @Override
    public List<Movie> getMovieInfoByTitle(String titleParam) {
        List<Movie> movieList = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT * FROM movies WHERE title = ?;";
            List<String> param = new ArrayList<>();
            param.add(titleParam);

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);

            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movie.setRating(getMovieRatingByMovieId(movieId));
                movieList.add(movie);
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }

    @Override
    public List<Movie> getMovieInfoByGenre(String genreParam) {
        List<Movie> movieList = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT * FROM movies WHERE genres LIKE ?;";
            List<String> param = new ArrayList<>();
            param.add("%" + genreParam + "%");

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);

            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movie.setRating(getMovieRatingByMovieId(movieId));
                movieList.add(movie);
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }

    @Override
    public List<Movie> getMovieInfoByYear(int yearParam) {
        List<Movie> movieList = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT * FROM movies WHERE year = ?;";
            List<Integer> param = new ArrayList<>();
            param.add(yearParam);

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);

            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                int year = rs.getInt("year");

                movie.setMovieId(movieId);
                movie.setTitle(title);
                if(!genres.equals("NULL")) movie.setGenres(genres);
                movie.setYear(year);
                movie.setRating(getMovieRatingByMovieId(movieId));
                movieList.add(movie);
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }

    public static double getMovieRatingByMovieId(int movieIdParam) {
        double rating = 0.0;
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT AVG(rating) AS rating FROM ratings WHERE movieId = ?;";
            List<Integer> param = new ArrayList<>();
            param.add(movieIdParam);

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);

            // Reading, analysing and saving the results...
            while(rs.next()) {
                rating = rs.getDouble("rating");
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Double.parseDouble(new DecimalFormat("######0.0").format(rating));
    }

    public static void main(String[] args) {
        System.out.println(new MovieInfoDaoImpl().getMovieInfoByMovieId(1));
        System.out.println(new MovieInfoDaoImpl().getMovieInfoByTitle("Sabrina"));
        System.out.println(new MovieInfoDaoImpl().getMovieInfoByGenre("Film-Noir"));
        System.out.println(new MovieInfoDaoImpl().getMovieInfoByYear(1920));
    }
}
