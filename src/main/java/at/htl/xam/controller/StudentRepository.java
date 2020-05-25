package at.htl.xam.controller;

import at.htl.xam.model.Quiz;
import at.htl.xam.model.Student;
import at.htl.xam.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student>{

    @Override
    public void save(Student student) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert student in database
            String sql = "INSERT INTO Student(name, username, password, class) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getsName());
            pstmt.setString(2, student.getsUsername());
            pstmt.setString(3, student.getsPassword());
            pstmt.setString(4, student.getsClassRoom());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete student from database
            String sql = "DELETE FROM Student WHERE student_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

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

    @Override
    public Student findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM student WHERE student_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                //Student(String sName, String sUsername, String sPassword, String sClassRoom)

                return new Student(rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("class"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}
