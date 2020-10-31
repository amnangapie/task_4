package ua.nure.sorokina.domain;

import ua.nure.sorokina.DBHelper;

import java.sql.SQLException;

public class Teacher {

    public static void createTable() throws SQLException {
        DBHelper.executeUpdate("CREATE TABLE IF NOT EXISTS Teachers " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "email VARCHAR(320) NOT NULL, " +
                "PRIMARY KEY (id))");
    }

    public static void insert(String firstname, String lastname, String email)
            throws SQLException {
        DBHelper.executeUpdate("INSERT INTO Teachers " +
                "(first_name, last_name, email) " + "VALUES ('" +
                firstname + "', '" + lastname + "', '" + email + "')");
    }
}
