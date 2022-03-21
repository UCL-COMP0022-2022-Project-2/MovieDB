package ucl.comp0022.team2.dao.impl;

import org.springframework.stereotype.Repository;
import ucl.comp0022.team2.dao.interfaces.SearchingReportDao;
import ucl.comp0022.team2.helper.MySQLHelper;
import ucl.comp0022.team2.model.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SearchingReportDaoImpl implements SearchingReportDao {

    @Override
    public List<Report> getReport(int movieId) {
        List<Report> movie_report = new ArrayList<>();
        try {
            // Connection to the database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT * FROM ratings r LEFT OUTER JOIN tags t ON t.userId = r.userId AND t.movieId = r.movieId WHERE r.movieId = ?";
            //join tags and ratings

            List<Integer> param = new ArrayList<>();
            param.add(movieId);

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, param);
            // Reading, analysing and saving the results...
            while(rs.next()) {
                Report report = new Report();
                int userID = rs.getInt("r.userID");
                double rating = rs.getDouble("rating");
                String tag = rs.getString("tag");

                Date tagTimestamp = rs.getDate("t.timestamp");
                Date ratingTimestamp = rs.getDate("r.timestamp");

                report.setUserId(userID);
                report.setRating(rating);
                report.setTags(tag);
                report.setTagTimestamp(tagTimestamp);
                report.setRatingTimestamp(ratingTimestamp);
                movie_report.add(report);
            }

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie_report;
    }

    public static void main(String[] args) {
        System.out.println(new SearchingReportDaoImpl().getReport(1));
    }
}
