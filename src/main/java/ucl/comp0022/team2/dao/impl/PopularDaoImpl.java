package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PopularDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PopularDaoImpl implements PopularDao {

    @Override
    public List<HashMap<Integer, Double>> getPopularMovieAvgList() {
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();
            // Writing sql and parameters...
            String sql = "SELECT IFNULL(avg(r.rating), 0) score, m.movieId FROM movies m\n" +
                    "LEFT JOIN ratings r ON m.movieId = r.movieId\n" +
                    "GROUP BY m.movieId ORDER BY score DESC;";
            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                HashMap<Integer, Double> map = new HashMap<>();
                Integer movieId = rs.getInt("movieId");
                Double score = Double.parseDouble(new DecimalFormat("######0.0").format(rs.getDouble("score")));
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
    @Override
    public List<HashMap<Integer, Double>> getPopularMovieTagsList() {
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT COUNT(t.movieId) score, m.movieId FROM movies m\n" +
                    "LEFT JOIN tags t ON m.movieId = t.movieId\n" +
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
            String sql = "SELECT COUNT(r.movieId) score, m.movieId FROM movies m\n" +
                    "LEFT JOIN ratings r ON m.movieId = r.movieId\n" +
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