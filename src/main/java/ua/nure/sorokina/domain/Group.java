package ua.nure.sorokina.domain;

import ua.nure.sorokina.DBHelper;

import java.sql.SQLException;

public class Group {

    public static void createTable() throws SQLException {
        DBHelper.executeUpdate("CREATE TABLE IF NOT EXISTS Groups " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "PRIMARY KEY (id))");
    }

    public static void insert(String name) throws SQLException {
        DBHelper.executeUpdate("INSERT INTO Groups (name) VALUES ('" + name + "')");
    }
}
