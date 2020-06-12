package at.htl.xam.controller;

import at.htl.xam.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements Repository<Question> {

    List<Question> questions = new ArrayList<>();

    @Override
    public void save(Question question) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            for (Question q : questions) {
                if (q.getQueId() == question.getQueId()) {
                    update(question);
                }
            }


            // TODO: Insert question in database
            String sql = "INSERT INTO Question(que_headline, que_description, que_result, que_quiz_id) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, question.getQueHeadline());
            pstmt.setString(2, question.getQueDesc());
            pstmt.setString(3, question.getQueResult());
            pstmt.setLong(4, 1);


            pstmt.execute();
            questions.add(question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(Question question) {

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "UPDATE Question SET que_headline = ?, que_description = ?, que_result = ?, que_quiz_id = ? WHERE que_question_id = " + question.getQueId();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, question.getQueHeadline());
            pstmt.setString(2, question.getQueDesc());
            pstmt.setString(3, question.getQueResult());
            pstmt.setLong(4, question.getQueQuiz().getQuiId());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "DELETE FROM Question WHERE que_question_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.execute();
            questions.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions_found = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all questions from database
            String sql = "SELECT que_question_id, que_headline, que_description, que_result FROM Question";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("que_question_id");
                String headline = result.getString("que_headline");
                String description = result.getString("que_description");
                String qResult = result.getString("que_result");
                questions_found.add(new Question(id, headline, description, qResult));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions_found;
    }

    @Override
    public Question findById(Long id) {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {
            String sql = "SELECT * FROM Question WHERE que_question_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                Question q = new Question();
                q.setQueId(id);
                rs.next();
                String headline = rs.getString("que_headline");
                q.setQueHeadline(headline);
                return q;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}