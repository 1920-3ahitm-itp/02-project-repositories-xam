package at.htl.xam.controller;

import at.htl.xam.model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository implements Repository<Quiz> {

    List<Quiz> quizzes = new ArrayList<>();

    @Override
    public void save(Quiz quiz) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            for (Quiz q : quizzes) {
                if (q.getQuiId() == quiz.getQuiId()) {
                    update(quiz);
                }
            }


            // TODO: Insert quiz in database
            String sql = "INSERT INTO Quiz(name, description, teacher_id) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, quiz.getQuiName());
            pstmt.setString(2, quiz.getQuiDescription());
            pstmt.setLong(3, 1);


            pstmt.execute();
            quizzes.add(quiz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(Quiz quiz) {

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete quiz from database
            String sql = "UPDATE Quiz SET quiz_id = ?, name = ?, description = ?, teacher_id = ?";
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

            // TODO: Delete quiz from database
            String sql = "DELETE FROM Quiz WHERE quiz_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();
            quizzes.remove(Integer.parseInt(String.valueOf(id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Quiz> findAll() {
        List<Quiz> quizzes_found = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all quizzes from database
            String sql = "SELECT quiz_id, name, description FROM Quiz";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("quiz_id");
                String name = result.getString("name");
                String description = result.getString("description");
                quizzes_found.add(new Quiz(id, name, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes_found;
    }

    @Override
    public Quiz findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM Quiz WHERE quiz_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                Quiz q = new Quiz();
                q.setQuiId(id);
                rs.next();
                String name = rs.getString("name");
                q.setQuiName(name);
                return q;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}