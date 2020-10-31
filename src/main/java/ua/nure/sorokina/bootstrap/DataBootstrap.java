package ua.nure.sorokina.bootstrap;

import ua.nure.sorokina.domain.*;

import java.sql.SQLException;

public class DataBootstrap {

    public static void run() throws SQLException {

        Group.insert("SE-20-1");
        Group.insert("SE-20-2");

        Student.insert("Allen", "Brown", 1);
        Student.insert("Nickolas", "Stewart", 1);
        Student.insert("Lily", "Smith", 1);
        Student.insert("Connell", "Rooney", 2);
        Student.insert("Bob", "Lewis", 2);
        Student.insert("Marianne", "Lewis", 2);

        Teacher.insert("Santiago", "de la Rosa", "santiago@gmail.com");
        Teacher.insert("Maria Isabella", "Franco", "maribel.franco@gmail.com");
        Teacher.insert("Ulrich", "Schwarz", "ulrich.schwarz@gmail.com");

        Course.insert("Science, Technology & Society", 1);
        Course.insert("Security", 3);
        Course.insert("Machine Learning", 3);
        Course.insert("Programming", 3);
        Course.insert("Technical Project", 2);

        StudentCourse.insert(1, 1);
        StudentCourse.insert(1, 2);
        StudentCourse.insert(2, 2);
        StudentCourse.insert(1, 3);
        StudentCourse.insert(3, 5);
        StudentCourse.insert(4, 2);
        StudentCourse.insert(4, 3);
        StudentCourse.insert(4, 4);
        StudentCourse.insert(5, 4);
        StudentCourse.insert(6, 4);

        Mark.insert(2, 2, 5);
        Mark.insert(3, 5, 4);
        Mark.insert(3, 5, 3);
        Mark.insert(4, 3, 5);
        Mark.insert(4, 4, 4);
        Mark.insert(5, 4, 5);
        Mark.insert(6, 4, 5);
    }
}
