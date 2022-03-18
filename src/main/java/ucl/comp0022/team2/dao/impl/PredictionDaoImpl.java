package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.PredictionDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
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
@Repository
public class PredictionDaoImpl implements PredictionDao {
    @Override
    public List<List<Object>> getForecastMovieList() {
        // minimum votes required to be listed
        int minimum = 5;
        List<List<Object>> list = new ArrayList<>();
        try {
            Connection conn = MySQLHelper.getConnection();
            String sql = "SELECT AVG(rating) avg FROM ratings;";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);
            double allMoviesAvg = 0d;
            while(rs.next()) {
                allMoviesAvg = rs.getDouble("avg");
            }
            String sql2 = "SELECT ((a.count / (a.count+" +minimum+ ")) * a.avg " +
                    "+ (" +minimum+ " / (a.count+" +minimum+ ")) * " +allMoviesAvg+")" +
                    " score,a.movieId movieId FROM (SELECT IFNULL(AVG(r.rating),0) avg,count(r.movieId) count, m.movieId FROM movies m" +
                    " INNER JOIN (SELECT t.rating, t.movieId, @rowNum := IF(@current_movieId = t.movieId, @rowNum+1,1) number," +
                    " @current_movieId := t.movieId FROM ratings t,(SELECT(@rowNum:=0),(@current_movieId:=0)) b ORDER BY t.movieId, `timestamp`) r ON m.movieId = r.movieId" +
                    " WHERE r.number <= 10 GROUP BY m.movieId) a ORDER BY score DESC;";
            // Close the connection to release resources...
            ResultSet rs2 = MySQLHelper.executingQuery(conn, sql2, null);
            // Reading, analysing and saving the results...
            while(rs2.next()) {
                List<Object> tinyList = new ArrayList<>(2);
                Integer movieId = rs2.getInt("movieId");
                double score = rs2.getDouble("score");
                //round to 2
                score = (double) Math.round(score * 100) / 100;
                tinyList.add(movieId);
                tinyList.add(score);
                list.add(tinyList);
            }
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean initialize() {
        Connection conn = null;
        try {
            //delete the existing data
            conn = MySQLHelper.getConnection();
            String clearSql = "delete from predicted_rating where true";
            MySQLHelper.executeUpdate(conn, clearSql, null);
            //obtain the calculated scores
            List<List<Object>> result = getForecastMovieList();
            //insert the values
            String insertSql = "insert into predicted_rating values(?, ?)";
            int rowsAffected = MySQLHelper.executeMultipleRowUpdate(conn, insertSql, result);
            if(rowsAffected > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(conn);
        }
        return false;
    }

    @Override
    public double getPredictionScoreById(int movieId) {
        Connection conn = null;
        double score = 0;
        try{
            conn = MySQLHelper.getConnection();
            String sql = "select rating from predicted_rating where movieId = ?";
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, Collections.singletonList(movieId));
            rs.next();
            score = rs.getDouble("rating");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            MySQLHelper.closeConnection(conn);
        }
        return score;
    }

    public static void main(String[] args) {
//        System.out.println(new PredictionDaoImpl().getForecastMovieList());
        System.out.println(new PredictionDaoImpl().initialize());
    }

}