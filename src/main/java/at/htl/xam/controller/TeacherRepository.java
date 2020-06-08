package at.htl.xam.controller;

import at.htl.xam.model.Question;
import at.htl.xam.model.Quiz;
import at.htl.xam.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements Repository<Teacher>{

    List<Teacher> teachers = new ArrayList<>();

    @Override
    public void save(Teacher teacher) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            for (Teacher t : teachers) {
                if (t.gettId() == teacher.gettId()) {
                    update(teacher);
                }
            }

            // TODO: Insert teacher in database
            String sql = "INSERT INTO Teacher(name, username, password) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, teacher.gettName());
            pstmt.setString(2, teacher.gettUsername());
            pstmt.setString(3, teacher.gettPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete teacher from database
            String sql = "DELETE FROM Teacher WHERE teacher_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();
            teachers.remove(Integer.parseInt(String.valueOf(id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all teachers from database
            String sql = "SELECT teacher_id, name, username, password FROM Teacher";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("teacher_id");
                String name = result.getString("name");
                String username = result.getString("username");
                String password = result.getString("password");
                teachers.add(new Teacher(id, name, username, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    @Override
    public Teacher findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                return new Teacher(rs.getString("name"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void update(Teacher teacher) {

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "UPDATE Teacher SET TEACHER_ID = ?, NAME = ?, USERNAME = ?, PASSWORD = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, teacher.gettId());
            pstmt.setString(2, teacher.gettName());
            pstmt.setString(3, teacher.gettUsername());
            pstmt.setString(4, teacher.gettPassword());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
