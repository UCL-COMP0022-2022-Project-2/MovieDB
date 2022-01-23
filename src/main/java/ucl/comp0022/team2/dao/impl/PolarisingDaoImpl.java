package ucl.comp0022.team2.dao.impl;

import ucl.comp0022.team2.dao.interfaces.PolarisingDao;
import ucl.comp0022.team2.helper.MySQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class PolarisingDaoImpl implements PolarisingDao {

    @Override
    public List<HashMap.Entry<String, Integer>> getRanking() {

        List<HashMap.Entry<String, Integer>> differenceRank = null;
        try {
            // Connection to the MySQL database
            Connection conn = MySQLHelper.getConnection();

            // Writing sql and parameters
            String sql = "SELECT m.genres, r.rating FROM movies AS m INNER JOIN ratings AS r WHERE m.movieId = r.movieId;";

            // Executing queries
            ResultSet rs = MySQLHelper.executingQuery(conn, sql, null);

            // Reading, analysing and saving the results
            HashMap<String, int[]> mapHighestAndLowest = new HashMap<>();
            HashMap<String, Integer> mapDifference = new HashMap<>();
            while(rs.next()) {
                String genres = rs.getString("genres");
                int rating = rs.getInt("rating");
                if(!genres.equals("NULL")) {
                    String[] splitGenres = genres.split("\\|");
                    int[] tmp = new int[2];
                    for (String genre : splitGenres) {
                        if (mapHighestAndLowest.containsKey(genre)) {
                            tmp[0] = rating > 3 ? mapHighestAndLowest.get(genre)[0] + 1 : mapHighestAndLowest.get(genre)[0]; // counter for score >= 4
                            tmp[1] = rating < 3 ? mapHighestAndLowest.get(genre)[1] - 1 : mapHighestAndLowest.get(genre)[1]; // counter for score <= 2
                        } else {
                            tmp = new int[]{0, 0};
                        }
                        mapHighestAndLowest.put(genre, tmp);
                    }
                }
            }
            for(String key : mapHighestAndLowest.keySet()) {
                int difference = mapHighestAndLowest.get(key)[0] - mapHighestAndLowest.get(key)[1];
                mapDifference.put(key, difference);
            }

            differenceRank = new ArrayList<>(mapDifference.entrySet());
            // differenceRank.sort(Map.Entry.comparingByValue());

            // Close the connection to release resources
            MySQLHelper.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differenceRank;
    }

    public static void main(String[] args) {
        System.out.println(new PolarisingDaoImpl().getRanking());
    }
}
