package ua.nure.sorokina;

import ua.nure.sorokina.bootstrap.DataBootstrap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

public class SqlTask {
    private static final int NUM_OF_STUDENTS = 2000;
    private static final int MARK = 4;

    public static void main(String[] args) {
        try {
            DBHelper.openConnection();
            DBHelper.createStatement();

            DBHelper.dropTables();
            DBHelper.createTables();
            DataBootstrap.run();

            getTeachersWithMoreThanXStudents(NUM_OF_STUDENTS);
            getExcellentStudentsInProgramming(MARK);
            getGroupStatistics();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeStatement();
                DBHelper.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static TreeSet<String> getTeachersWithMoreThanXStudents(int n) throws SQLException {
        TreeSet<String> teachersInfo = new TreeSet<>();
        ResultSet rs = DBHelper.executeQuery("SELECT "
                + "Courses_info.teacher_id, "
                + "Teachers.first_name, Teachers.last_name, Teachers.email "
                + "FROM Teachers, (SELECT Courses.id, "
                + "Courses.teacher_id as teacher_id, "
                + "COUNT(Students.id) as students "
                + "FROM Students, Courses, Student_Courses "
                + "WHERE Students.id = Student_Courses.student_id "
                + "AND Courses.id = Student_Courses.course_id "
                + "GROUP BY Courses.id) as Courses_info "
                + "WHERE Teachers.id = Courses_info.teacher_id "
                + "GROUP BY teacher_id "
                + "HAVING COUNT(Courses_info.students) > " + n);

        while (rs.next()) {
            String record = rs.getInt("teacher_id") + ": "
                    + rs.getString("first_name") + " "
                    + rs.getString("last_name") + ", "
                    + rs.getString("email");
            teachersInfo.add(record);
            System.out.println(record);
        }
        return teachersInfo;
    }

    public static TreeSet<String> getExcellentStudentsInProgramming(int mark) throws SQLException {
        TreeSet<String> studentsInfo = new TreeSet<>();
        ResultSet rs = DBHelper.executeQuery("SELECT Students.id, "
                + "Students.first_name, Students.last_name "
                + "FROM Students, Marks "
                + "WHERE Students.id = Marks.student_id "
                + "AND Marks.course_id = (SELECT id FROM Courses "
                + "WHERE Courses.course_title = 'Programming') "
                + "AND Marks.value > " + mark + " "
                + "ORDER BY last_name, first_name");

        while (rs.next()) {
            String record = rs.getString("id") + ": "
                    + rs.getString("last_name") + " "
                    + rs.getString("first_name");
            studentsInfo.add(record);
            System.out.println(record);
        }
        return studentsInfo;
    }

    public static TreeSet<String> getGroupStatistics() throws SQLException {
        TreeSet<String> groupInfo = new TreeSet<>();
        ResultSet rs = DBHelper.executeQuery("SELECT Groups.name, "
                + "COUNT(Students.id) as students, "
                + "A.excellent_students, B.teachers FROM Students "
                + "JOIN Groups ON Groups.id = Students.group_id "
                + "JOIN (SELECT COUNT(Students.id) as excellent_students, "
                + "Students.group_id as group_id FROM Students "
                + "WHERE Students.id IN (SELECT Marks.student_id FROM Marks) "
                + "AND Students.id NOT IN (SELECT Marks.student_id FROM Marks "
                + "WHERE Marks.value < 5) GROUP BY Students.group_id) A "
                + "ON A.group_id = Students.group_id "
                + "JOIN (SELECT COUNT(DISTINCT Courses.teacher_id) as teachers, "
                + "Students.group_id as group_id "
                + "FROM Courses, Student_Courses, Students "
                + "WHERE Student_Courses.course_id = Courses.id "
                + "AND Students.id = Student_Courses.student_id "
                + "GROUP BY Students.group_id) B "
                + "ON B.group_id = Students.group_id GROUP BY Students.group_id ");

        while (rs.next()) {
            String record = rs.getString("name") + ": students - "
                    + rs.getInt("students") + ", excellent students - "
                    + rs.getInt("excellent_students") + ", teachers - "
                    + rs.getInt("teachers");
            groupInfo.add(record);
            System.out.println(record);
        }
        return groupInfo;
    }
}
