package at.htl.xam.controller;

import at.htl.xam.model.Student;
import at.htl.xam.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentRepository {
    private StudentRepository studentRepository = new StudentRepository();

    public StudentRepository() {
        if (!tableExists()) {
            createTable();
        }
    }

    public void createTable() {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "CREATE TABLE Student(" +
                    "student_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    "name varchar(50) NOT NULL," +
                    "username varchar(50) NOT NULL," +
                    "password varchar(32) NOT NULL," +
                    "class varchar(6) NOT NULL," +
                    "CONSTRAINT PK_Student PRIMARY KEY (student_id)" +
                    ")";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Created table Student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists() {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'Student'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void addStudent(Student student) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert student in database
            String sql = "INSERT INTO Student(name, username, password, class) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getUsername());
            pstmt.setString(3, student.getPassword());
            pstmt.setString(4, student.getClassRoom());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all students from database
            String sql = "SELECT student_id, name, username, password, class FROM Student";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("student_id");
                String name = result.getString("name");
                String username = result.getString("username");
                String password = result.getString("password");
                String classRoom = result.getString("class");
                students.add(new Student(id, name, username, password, classRoom));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }


    public void removeStudent(Student student) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete student from database
            String sql = "DELETE FROM Student WHERE student_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, student.getId());

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveStudent(Student student) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Update question in database
            String sql = "UPDATE Student SET name = ?, username = ?, password = ?,  class = ? WHERE student_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getUsername());
            pstmt.setString(3, student.getUsername());
            pstmt.setString(4, student.getClassRoom());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
