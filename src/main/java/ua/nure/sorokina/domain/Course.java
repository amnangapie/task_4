package ua.nure.sorokina.domain;

import ua.nure.sorokina.DBHelper;

import java.sql.SQLException;

public class Course {

    public static void createTable() throws SQLException {
        DBHelper.executeUpdate("CREATE TABLE IF NOT EXISTS Courses " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "course_title VARCHAR(50) NOT NULL, " +
                "teacher_id INTEGER NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (teacher_id) REFERENCES Teachers(id))");
    }

    public static void insert(String title, int teacherId) throws SQLException {
        DBHelper.executeUpdate("INSERT INTO Courses " +
                "(course_title, teacher_id) " +
                "VALUES ('" + title + "', " + teacherId +")");
    }
}
