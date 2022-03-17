package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PolarisingDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PolarisingDaoImpl implements PolarisingDao {

    @Override
    public List<Movie> getPolarisingMovieList(String limit) {
        List<Movie> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "select movies.movieId, movies.title, movies.genres, movies.year, AVG(rating) as avg_rating\n" +
                    "from movies left join ratings r on movies.movieId = r.movieId\n" +
                    "group by movies.movieId\n" +
                    "order by VARIANCE(rating) desc\n" +
                    "limit " + limit + ";";
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                int movieId = rs.getInt("movieId");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                Double rating = rs.getDouble("avg_rating");
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

    public static void main(String[] args) {
        System.out.println(new PolarisingDaoImpl().getPolarisingMovieList("0, 50"));
    }
}