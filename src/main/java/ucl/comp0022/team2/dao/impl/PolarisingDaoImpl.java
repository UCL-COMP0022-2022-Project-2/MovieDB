package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PolarisingDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PolarisingDaoImpl implements PolarisingDao {

    @Override
    public List<Movie> getPolarisingMovieList() {
        List<Movie> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "select (count(if (r.rating > 4, r.movieId, null)) + count(if (r.rating < 2, r.movieId, null))) " +
                    "/count(r.movieId) rating,m.* from ratings r\n" +
                    "left join movies m on m.movieId = r.movieId\n" +
                    "GROUP BY r.movieId order by rating desc;";

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movieId"));
                movie.setYear(rs.getInt("year"));
                movie.setTitle(rs.getString("title"));
                movie.setRating(rs.getDouble("rating"));

                String genres = rs.getString("genres");
                if(!genres.equals("NULL")){
                    movie.setGenres(genres);
                }
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