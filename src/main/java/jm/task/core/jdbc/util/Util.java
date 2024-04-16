package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    static Properties properties;
    static String url;
    static String username;
    static String password;

    public static Connection getCon() throws IOException, SQLException {
        if (properties == null) {
            properties = new Properties();
            properties.load(new FileInputStream("database.properties"));
            password = properties.getProperty("db.password");
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.user");
        }

        return DriverManager.getConnection(url, username, password);
    }
}