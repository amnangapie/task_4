package ua.nure.sorokina;

import ua.nure.sorokina.domain.*;

import java.sql.*;

public class DBHelper {
    static final String JDBC_DRIVER = SqlConstants.JDBC_DRIVER;
    static final String DB_URL = SqlConstants.DB_URL;
    static final String USER = SqlConstants.USER;
    static final String PASSWORD = SqlConstants.PASSWORD;
    static Connection connection = null;
    static Statement statement = null;

    public static void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void createStatement() throws SQLException {
        if (connection != null) {
           statement = connection.createStatement();
        }
    }

    public static void closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        ResultSet resultSet = null;
        if (statement != null) {
            resultSet = statement.executeQuery(sql);
        }
        return resultSet;
    }

    public static boolean executeUpdate(String sql) throws SQLException {
        if (statement != null) {
            statement.executeUpdate(sql);
        }
        return statement != null;
    }

    public static void dropTables() throws SQLException {
        DBHelper.executeUpdate("DROP ALL OBJECTS");
    }

    public static void createTables() throws SQLException {
        Group.createTable();
        Teacher.createTable();
        Student.createTable();
        Course.createTable();
        StudentCourse.createTable();
        Mark.createTable();
    }
}
