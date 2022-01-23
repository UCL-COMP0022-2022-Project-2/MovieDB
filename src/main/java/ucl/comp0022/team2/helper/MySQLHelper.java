package ucl.comp0022.team2.helper;

import java.sql.*;
import java.util.List;

public class MySQLHelper {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/moviedb"; //172.17.0.2
    private static final String username = "root";
    private static final String password = "comp0022team2";

    /**
     * Getting MySQL database connection
     * @return MySQL database connection object
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Closing MySQL database connection
     * @param conn MySQL database connection object
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Executing MySQL Query(ies)
     * @param conn MySQL database connection object
     * @param sql sql need to be executed
     * @param param a list of parameter(s) to replace '?' in sql
     * @return ResultSet stored all results
     */
    public static ResultSet executingQuery(Connection conn, String sql, List param) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(param != null && param.size() > 0) {
                for(int i = 0; i < param.size(); i++) {
                    ps.setObject(i + 1, param.get(i));
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Executing MySQL Update(s)
     * @param conn MySQL database connection object
     * @param sql sql need to be executed
     * @param param a list of parameter(s) to replace '?' in sql
     * @return number of revised line(s)
     */
    public static int executeUpdate(Connection conn, String sql, List param) {
        int line = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(param != null && param.size() > 0) {
                for(int i = 0; i < param.size(); i++) {
                    ps.setObject(i + 1, param.get(i));
                }
            }
            line = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
