package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PopularDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class PopularDaoImpl implements PopularDao {

    @Override
    public List<HashMap.Entry<String, Double>> getRanking() {

        List<HashMap.Entry<String, Double>> scoreRank = null;
        try {
            // Connection to the MySQL database...
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters...
            String sql = "SELECT m.genres, r.rating FROM movies AS m INNER JOIN ratings AS r WHERE m.movieId = r.movieId;";

            // Executing queries...
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);

            // Reading, analysing and saving the results...
            HashMap<String, int[]> mapTotalScoreAndTimes = new HashMap<>();
            HashMap<String, Double> mapAverageScore = new HashMap<>();
            while(rs.next()) {
                String genres = rs.getString("genres");
                int rating = rs.getInt("rating");
                if(!genres.equals("NULL")) {
                    String[] splitGenres = genres.split("\\|");
                    int[] tmp = new int[2];
                    for (String genre : splitGenres) {
                        if (mapTotalScoreAndTimes.containsKey(genre)) {
                            tmp[0] = mapTotalScoreAndTimes.get(genre)[0] + rating; // total score
                            tmp[1] = mapTotalScoreAndTimes.get(genre)[1] + 1; // counting times
                        } else {
                            tmp = new int[]{0, 0};
                        }
                        mapTotalScoreAndTimes.put(genre, tmp);
                    }
                }
            }
            for(String key : mapTotalScoreAndTimes.keySet()) {
                double averageScore = (double) mapTotalScoreAndTimes.get(key)[0] / mapTotalScoreAndTimes.get(key)[1];
                mapAverageScore.put(key, averageScore);
            }
            scoreRank = new ArrayList<>(mapAverageScore.entrySet());
            // scoreRank.sort(Map.Entry.comparingByValue());
            // Collections.reverse(scoreRank);

            // Close the connection to release resources...
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreRank;
    }

    public static void main(String[] args) {
        System.out.println(new PopularDaoImpl().getRanking());
    }
}
