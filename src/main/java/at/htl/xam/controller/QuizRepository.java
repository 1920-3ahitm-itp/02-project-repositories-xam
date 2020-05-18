package at.htl.xam.controller;

import at.htl.xam.model.Question;
import at.htl.xam.model.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizRepository {

    public void dropTable() {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "DROP TABLE Quiz";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Dropped table Quiz.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists() {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'Quiz'";
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

    public void addQuiz(Quiz quiz) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert question in database
            String sql = "INSERT INTO Quiz(quiz_id, name, description, teacher_id) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, quiz.getId());
            pstmt.setString(2, quiz.getName());
            pstmt.setString(3, quiz.getDescription());
            pstmt.setLong(4, 1);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getAllQuestions() {
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

    public void removeQuestion(int id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "DELETE FROM Question WHERE question_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveQuestion(Question question) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Update question in database
            String sql = "UPDATE Question SET headline = ?, description = ?, result = ? WHERE question_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, question.getHeadline());
            pstmt.setString(2, question.getDesc());
            pstmt.setString(3, question.getResult());
            pstmt.setLong(4, question.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
