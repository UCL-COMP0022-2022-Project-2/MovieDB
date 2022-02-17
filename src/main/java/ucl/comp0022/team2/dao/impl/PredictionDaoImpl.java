package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PredictionDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 公式如下：
 * 　　weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C
 * 　　其中：
 * 　　R = average for the movie (mean) = (Rating) （是用普通的方法计算出的平均分）
 * 　　v = number of votes for the movie = (votes) （投票人数，需要注意的是，只有经常投票者才会被计算在内，这个下面详细解释）
 * 　　m = minimum votes required to be listed in the top 250 (currently 1250) （进入imdb top 250需要的最小票数，只有三两个人投票的电影就算得满分也没用的）
 * 　　C = the mean vote across the whole report (currently 6.9) （目前所有电影的平均得分）
 *    本次计算 m 取250，可调整，C = allMoviesAvg
 */
public class PredictionDaoImpl implements PredictionDao {
    @Override
    public List<HashMap<Integer, Double>> getForecastMovieList() {
        // minimum votes required to be listed
        int minimum = 250;
        // 电影评论超过10才计算
        int minRatingCount = 10;
        List<HashMap<Integer, Double>> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            // 先计算目前所有电影的平均得分
            String sql = "SELECT AVG(rating) avg FROM ratings;";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            // 目前所有电影的平均得分
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
