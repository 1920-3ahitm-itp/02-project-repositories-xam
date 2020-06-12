package at.htl.xam.controller;


import at.htl.xam.model.Student;
import at.htl.xam.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements Repository<Student> {

    List<Student> students = new ArrayList<>();

    @Override
    public void save(Student student) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            for (Student s : students) {
                if (s.getsId() == student.getsId()) {
                    update(student);
                }
            }


            // TODO: Insert student in database
            String sql = "INSERT INTO Student(s_name, s_username, s_password, s_class) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, student.getsName());
            pstmt.setString(2, student.getsUsername());
            pstmt.setString(3, student.getsPassword());
            pstmt.setString(4, student.getsClassRoom());


            pstmt.execute();
            students.add(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(Student student) {

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete student from database
            String sql = "UPDATE Student SET s_student_id = ?, s_name = ?, s_username = ?, s_password = ?, s_class = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, student.getsId());
            pstmt.setString(2, student.getsName());
            pstmt.setString(3, student.getsUsername());
            pstmt.setString(4, student.getsPassword());
            pstmt.setString(5, student.getsClassRoom());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete student from database
            String sql = "DELETE FROM Student WHERE s_student_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();
            students.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students_found = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all students from database
            String sql = "SELECT s_student_id, s_name, s_username, s_password, s_class FROM Student";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("s_student_id");
                String name = result.getString("s_name");
                String username = result.getString("s_username");
                String password = result.getString("s_password");
                String classRoom = result.getString("s_class");
                students_found.add(new Student(id, name, username, password, classRoom));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students_found;
    }


    @Override
    public Student findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM Student WHERE s_student_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                Student s = new Student();
                s.setsId(id);
                rs.next();
                String name = rs.getString("s_name");
                s.setsName(name);
                return s;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}