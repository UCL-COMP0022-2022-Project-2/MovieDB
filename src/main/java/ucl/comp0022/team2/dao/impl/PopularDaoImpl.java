package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PolarisingDao;
import ucl.comp0022.team2.dao.interfaces.PopularDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PopularDaoImpl implements PopularDao {

    @Override
    public List<Movie> getPopularMovieList() {
        List<Movie> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "select m.*, (IFNULL(b.avg,0) * 0.3 + IFNULL(a.count,0) * 0.4 + IFNULL(b.count,0) * 0.3) rating\n" +
                    "from movies m \n" +
                    "left join (select movieId,IFNULL(count(*),0) count from tags GROUP BY movieId) a on m.movieId = a.movieId\n" +
                    "left join (select movieId,IFNULL(count(*),0) count,avg(rating) avg from ratings GROUP BY movieId) b on m.movieId = b.movieId\n" +
                    "order by rating desc;";

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
        System.out.println(new PopularDaoImpl().getPopularMovieList());
    }
}