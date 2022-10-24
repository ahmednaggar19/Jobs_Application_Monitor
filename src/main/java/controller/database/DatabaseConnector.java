package controller.database;

import java.sql.*;

public class DatabaseConnector {

    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String SCHEMA_NAME = "Application_Monitor";

    private static final String CONNECTION_NAME = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    private static Connection con;

    static {
        registerDriver();
        initializeConnection();
    }

    private static void registerDriver() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initializeConnection() {
        try {
            con = DriverManager.getConnection(CONNECTION_NAME + SCHEMA_NAME, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean executeModify(String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
