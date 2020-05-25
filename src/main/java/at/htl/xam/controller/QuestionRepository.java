package at.htl.xam.controller;

import at.htl.xam.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements Repository<Question> {

    @Override
    public void save(Question question) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert question in database
            String sql = "INSERT INTO Question(headline, description, result, QUIZ_ID) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, question.getQueHeadline());
            pstmt.setString(2, question.getQueDesc());
            pstmt.setString(3, question.getQueResult());
            pstmt.setLong(4, 1);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "DELETE FROM Question WHERE question_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all questions from database
            String sql = "SELECT question_id, headline, description, result FROM Question";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("question_id");
                String headline = result.getString("headline");
                String description = result.getString("description");
                String qResult = result.getString("result");
                questions.add(new Question(id, headline, description, qResult));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    @Override
    public Question findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM Question WHERE question_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                Question q = new Question();
                q.setQueId(id);
                rs.next();
                String headline = rs.getString("headline");
                q.setQueHeadline(headline);
                return q;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}
