package at.htl.xam.controller;

import at.htl.xam.model.Quiz;
import at.htl.xam.model.Teacher;

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

    public void addQuiz(Quiz quiz, Teacher teacher) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert question in database
            String sql = "INSERT INTO Quiz(quiz_id, name, description, teacher_id) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, quiz.getQuiId());
            pstmt.setString(2, quiz.getQuiName());
            pstmt.setString(3, quiz.getQuiDescription());
            pstmt.setLong(4, teacher.gettId());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all questions from database
            String sql = "SELECT quiz_id, name, description, teacher_id FROM Quiz";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("quiz_id");
                String name = result.getString("name");
                String description = result.getString("description");
                long teacher_id = result.getInt("teacher_id");
                quizzes.add(new Quiz(id, name, description, teacher_id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public void removeQuiz(int id) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete question from database
            String sql = "DELETE FROM Question WHERE quiz_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveQuiz(Quiz quiz) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Update question in database
            String sql = "UPDATE Quiz SET name = ?, description = ?, teacher_id = ? WHERE QUIZ_ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, quiz.getQuiName());
            pstmt.setString(2, quiz.getQuiDescription());
            pstmt.setLong(3, quiz.getQuiTeacher_id());
            pstmt.setLong(4, quiz.getQuiId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
