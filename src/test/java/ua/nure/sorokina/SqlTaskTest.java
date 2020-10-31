package ua.nure.sorokina;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.nure.sorokina.bootstrap.DataBootstrap;

import java.sql.SQLException;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

class SqlTaskTest {

    @BeforeAll
    static void beforeAll() throws SQLException, ClassNotFoundException {
        DBHelper.openConnection();
        DBHelper.createStatement();

        DBHelper.dropTables();
        DBHelper.createTables();
        DataBootstrap.run();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        DBHelper.closeStatement();
        DBHelper.closeConnection();
    }

    @Test
    void getTeachersWithMoreThanXStudents() throws SQLException {
        TreeSet<String> teachers = SqlTask.getTeachersWithMoreThanXStudents(2);
        String teacher = "3: Ulrich Schwarz, ulrich.schwarz@gmail.com";

        assertEquals(teacher, teachers.pollFirst());
    }

    @Test
    void getExcellentStudentsInProgramming() throws SQLException {
        TreeSet<String> students = SqlTask.getExcellentStudentsInProgramming(4);
        String student = "5: Lewis Bob";

        assertEquals(student, students.pollFirst());
    }

    @Test
    void getGroupStatistics() throws SQLException {
        TreeSet<String> groupStatistics = SqlTask.getGroupStatistics();
        String groupInfo = "SE-20-1: students - 3, excellent students - 1, teachers - 3";

        assertEquals(groupInfo, groupStatistics.pollFirst());
    }
}