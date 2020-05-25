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

public class QuizRepository implements Repository<Quiz> {

    @Override
    public void save(Quiz quiz) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert question in database
            String sql = "INSERT INTO Quiz(quiz_id, name, description, teacher_id) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, quiz.getQuiId());
            pstmt.setString(2, quiz.getQuiName());
            pstmt.setString(3, quiz.getQuiDescription());
            pstmt.setLong(4, quiz.getQuiTeacher().gettId());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Long id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "DELETE FROM Question WHERE quiz_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Quiz> findAll() {
        List<Quiz> quizzes = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all questions from database
            String sql = "SELECT quiz_id, name, description, teacher_id FROM Quiz";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                long id = result.getInt("quiz_id");
                String name = result.getString("name");
                String description = result.getString("description");
                Long teacher_id = result.getLong("teacher_id");
                Teacher teacher1 = null;
                for (Teacher teacher : Quiz.teachers) {
                    if (teacher.gettId() == teacher_id) {
                        teacher1 = teacher;
                    }
                }
                quizzes.add(new Quiz(id, name, description, teacher1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    @Override
    public Quiz findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                //Quiz(Long quiId, String quiName, String quiDescription, Teacher quiTeacher)
                Teacher quiTeacher = null;
                for (Teacher teacher : Quiz.teachers) {
                    if (teacher.gettId() == rs.getLong("teacher_id")) {
                        quiTeacher = teacher;
                    }
                }
                return new Quiz(rs.getLong("quiz_id"), rs.getString("name"), rs.getString("description"), quiTeacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}


