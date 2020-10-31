package ua.nure.sorokina.domain;

import ua.nure.sorokina.DBHelper;

import java.sql.SQLException;

public class StudentCourse {

    public static void createTable() throws SQLException {
        DBHelper.executeUpdate("CREATE TABLE IF NOT EXISTS Student_Courses " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "student_id INTEGER NOT NULL, " +
                "course_id INTEGER NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (student_id) REFERENCES Students(id), " +
                "FOREIGN KEY (course_id) REFERENCES Courses(id))");
    }

    public static void insert(int studentId, int courseId) throws SQLException {
        DBHelper.executeUpdate("INSERT INTO Student_Courses " +
                "(student_id, course_id) " +
                "VALUES (" + studentId + ", " + courseId +")");
    }
}
