package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String url = "jdbc:mysql://localhost/test";
    private static final String username = "root";
    private static final String password = "kadjitnichegonekral";

    private static Connection con;
    private static Statement statement;

    public static void init() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        statement = con.createStatement();
    }

    public static void closeConnection() throws SQLException {
        con.close();
    }

    public static void runUp(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    public static ResultSet runQuery(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }
}
