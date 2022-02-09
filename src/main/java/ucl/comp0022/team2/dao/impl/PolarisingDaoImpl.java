package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PolarisingDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PolarisingDaoImpl implements PolarisingDao {

    @Override
    public List<HashMap<Integer, Double>> getPolarisingMovieList() {
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT (COUNT(IF (r.rating > 4, r.movieId, null)) + COUNT(IF (r.rating < 2, r.movieId, null))) " +
                    "/ COUNT(r.movieId) AS score, m.movieId FROM movies AS m\n" +
                    "LEFT JOIN ratings AS r ON m.movieId = r.movieId\n" +
                    "GROUP BY m.movieId ORDER BY score DESC;";
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                HashMap<Integer, Double> map = new HashMap<>();
                Integer movieId = rs.getInt("movieId");
                Double score = rs.getDouble("score");
                map.put(movieId,score);
                list.add(map);
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