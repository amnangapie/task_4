package ua.nure.sorokina.domain;

import ua.nure.sorokina.DBHelper;

import java.sql.SQLException;

public class Student {

    public static void createTable() throws SQLException {
        DBHelper.executeUpdate("CREATE TABLE IF NOT EXISTS Students " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "group_id INTEGER NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (group_id) REFERENCES Groups(id))");
    }

    public static void insert(String firstname, String lastname, int group_id)
            throws SQLException {
        DBHelper.executeUpdate("INSERT INTO Students " +
                "(first_name, last_name, group_id) " + "VALUES ('" +
                firstname + "', '" + lastname + "', '" + group_id + "')");
    }
}
