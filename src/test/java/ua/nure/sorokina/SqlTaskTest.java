package ua.nure.sorokina;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.nure.sorokina.bootstrap.DataBootstrap;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

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
        Set<String> teachers = SqlTask.getTeachersWithMoreThanXStudents(2);
        String teacher = "3: Ulrich Schwarz, ulrich.schwarz@gmail.com";

        assertEquals(teacher, teachers.stream().findAny().get());
    }

    @Test
    void getExcellentStudentsInProgramming() throws SQLException {
        Set<String> students = SqlTask.getExcellentStudentsInProgramming(4);
        String[] studentArray = new String[2];
        String student1 = "5: Lewis Bob";
        String student2 = "6: Lewis Marianne";
        Iterator<String> iterator = students.iterator();
        studentArray[0] = iterator.next();
        studentArray[1] = iterator.next();

        assertEquals(true,
                (studentArray[0].equals(student1))
                        && (studentArray[1].equals(student2))
                || (studentArray[1].equals(student1))
                        && (studentArray[0].equals(student2)));
    }

    @Test
    void getGroupStatistics() throws SQLException {
        Set<String> groupStatistics = SqlTask.getGroupStatistics();
        String[] groupArray = new String[2];
        String student1 = "SE-20-1: students - 3, excellent students - 1, teachers - 3";
        String student2 = "SE-20-2: students - 3, excellent students - 2, teachers - 1";
        Iterator<String> iterator = groupStatistics.iterator();
        groupArray[0] = iterator.next();
        groupArray[1] = iterator.next();

        assertEquals(true,
                (groupArray[0].equals(student1))
                        && (groupArray[1].equals(student2))
                        || (groupArray[1].equals(student1))
                        && (groupArray[0].equals(student2)));
    }
}