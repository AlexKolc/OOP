package utils;

import java.sql.*;

public class ConnectionUtil {
    private static final String url = "jdbc:sqlserver://localhost:57928;database=ShopsInformation";
    private static final String login = "user";
    private static final String password = "qwerty";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}

