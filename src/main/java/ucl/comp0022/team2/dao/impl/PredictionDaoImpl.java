package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PredictionDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 　　weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C
 * 　
 * 　　R = average for the movie (mean) = (Rating)
 * 　　v = number of votes for the movie = (votes)
 * 　　m = minimum votes required to be listed in the top 250 (currently 1250)
 * 　　C = the mean vote across the whole report (currently 6.9)
 *    m=250 ,C = allMoviesAvg
 */
public class PredictionDaoImpl implements PredictionDao {
    @Override
    public List<HashMap<Integer, Double>> getForecastMovieList() {
        // minimum votes required to be listed
        int minimum = 250;
        int minRatingCount = 10;
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT AVG(rating) avg FROM ratings;";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            double allMoviesAvg = 0d;
            while(rs.next()) {
                allMoviesAvg = rs.getDouble("avg");
            }
            String sql2 = "select ((a.count / (a.count+" +minimum+ ")) * a.avg " +
                    "+ (" +minimum+ " / (a.count+" +minimum+ ")) * " +allMoviesAvg+")" +
                    " score,a.movieId movieId FROM (SELECT IFNULL(AVG(r.rating),0) avg,count(r.movieId) count, m.movieId FROM movies m\n" +
                    "INNER JOIN ratings r ON m.movieId = r.movieId\n" +
                    "GROUP BY m.movieId) a WHERE a.count >= "+minRatingCount+" ORDER BY score DESC;";
            // Close the connection to release resources...
            ResultSet rs2 = MySQLHelper.executingQuery(conn, sql2, null);
            // Reading, analysing and saving the results...
            while(rs2.next()) {
                HashMap<Integer, Double> map = new HashMap<>();
                Integer movieId = rs2.getInt("movieId");
                Double score = rs2.getDouble("score");
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
        System.out.println(new PredictionDaoImpl().getForecastMovieList());
    }

}
