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
            String sql = "INSERT INTO Teacher(t_name, t_username, t_password) VALUES(?, ?, ?)";
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
            String sql = "DELETE FROM Teacher WHERE t_teacher_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();
            teachers.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all teachers from database
            String sql = "SELECT t_teacher_id, t_name, t_username, t_password FROM Teacher";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("t_teacher_id");
                String name = result.getString("t_name");
                String username = result.getString("t_username");
                String password = result.getString("t_password");
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
            String sql = "SELECT * FROM quiz WHERE t_quiz_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                return new Teacher(rs.getString("t_name"), rs.getString("t_username"), rs.getString("t_password"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void update(Teacher teacher) {

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "UPDATE Teacher SET t_TEACHER_ID = ?, t_NAME = ?, t_USERNAME = ?, t_PASSWORD = ?";
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
