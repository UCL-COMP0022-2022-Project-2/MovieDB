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
    public List<Movie> getPolarisingMovieList() {
        List<Movie> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT m.movieID as movieID,m.title as title,m.genres as genres,"+
                    "m.year as year,sc.score as score, sc.avg_rating as avg_rating\n"+
                    "FROM movies AS m, ratings AS r,\n" +
                    "(SELECT IFNULL(VAR_POP(r.rating), 0) AS score, movieID,IFNULL(AVG(r.rating),0) AS  avg_rating\n"+
                    "FROM ratings AS r\n"+
                    "GROUP BY movieID) as sc\n"+
                    "WHERE sc.movieID = m.movieID\n"+
                    "and m.movieID = r.movieID\n"+
                    "ORDER BY score DESC;";
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
        System.out.println(new PolarisingDaoImpl().getPolarisingMovieList());
    }
}