package ucl.comp0022.team2.helper;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySQLHelper {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static ComboPooledDataSource getDataSource() {
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/moviedb");
            dataSource.setUser("root");
            dataSource.setPassword("comp0022team2");
            dataSource.setInitialPoolSize(10);
            dataSource.setMaxPoolSize(1000);
            dataSource.setMinPoolSize(10);
            dataSource.setMaxStatements(200);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * Getting MySQL database connection
     * @return MySQL database connection object
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
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

    /**
     * Executing MySQL Update(s)
     * @param conn MySQL database connection object
     * @param sql sql need to be executed
     * @param param a list of parameter(s) to replace '?' in sql
     * @return number of revised line(s)
     */
    public static int executeMultipleRowUpdate(Connection conn, String sql, List<List<Object>> param) {
        int[] result = null;
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(sql);
            for(List list:param){
                for(int i = 0; i < list.size(); i++){
                    ps.setObject(i+1, list.get(i));
                }
                ps.addBatch();
            }
            result = ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            try{
                conn.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
            finally {

                closeConnection(conn);
            }
        }
        return result == null?0:Arrays.stream(result).sum();
    }
}
