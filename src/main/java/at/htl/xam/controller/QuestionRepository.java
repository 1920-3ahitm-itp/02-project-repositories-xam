package at.htl.xam.controller;

import at.htl.xam.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    //private QuestionRepository questionRepository = new QuestionRepository();

//    public QuestionRepository() {
//        if (!tableExists()) {
//            createTable();
//        }
//    }

//    public void createTable() {
//        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {
//
//            String sql = "CREATE TABLE Question(" +
//                    "question_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
//                    "headline varchar(100)," +
//                    "description varchar(1000)," +
//                    "result varchar(100) NOT NULL," +
//                    "quiz_id int not null," +
//                    "CONSTRAINT PK_Question PRIMARY KEY (question_id)" +
//                    "CONSTRAINT FK_Question FOREIGN KEY (quiz_id) references Quiz(quiz_id)" +
//                    ")";
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.execute();
//            System.out.println("Created table Question.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void dropTable() {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "DROP TABLE Question";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Dropped table Question.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists() {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'Question'";
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

    public void addQuestion(Question question) {
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
            pstmt.setString(1, question.getQueHeadline());
            pstmt.setString(2, question.getQueDesc());
            pstmt.setString(3, question.getQueResult());
            pstmt.setLong(4, question.getQueId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Question findById(long id) {
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
