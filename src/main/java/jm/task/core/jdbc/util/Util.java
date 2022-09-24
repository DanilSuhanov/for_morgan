package jm.task.core.jdbc.util;

import java.sql.*;

public class Util implements AutoCloseable {

    private static final String url = "jdbc:mysql://localhost/test";
    private static final String username = "root";
    private static final String password = "kadjitnichegonekral";

    private static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Statement statement;

    static {
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void runUp(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    public static ResultSet runQuery(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
