package at.htl.xam.controller;

import at.htl.xam.database.SqlRunner;
import at.htl.xam.model.Question;
import at.htl.xam.model.Quiz;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

class QuestionRepositoryTest {

    @BeforeAll
    static void beforeAll() {
        SqlRunner.run();
    }

    @Order(1000)
    @Test
    void save() {
        //arrange
        QuestionRepository questionRepository = new QuestionRepository();
        Question question = new Question(1L, "QuestionHeadline", "QuestionDescription", "QuestionResult");
        Table t = new Table(DatasourceFactory.getDataSource(), "Question");
        output(t).toConsole();

        //act
        questionRepository.save(question);
        t = new Table(DatasourceFactory.getDataSource(), "Question");
        output(t).toConsole();

        //assert
        Assertions.assertThat(t).row(0)
                .row(t.getRowsList().size() - 1)
                .value("HEADLINE").isEqualTo("QuestionHeadline");
    }

    @Order(2000)
    @Test
    void delete() {
        //arrange
        QuestionRepository questionRepository = new QuestionRepository();
        questionRepository.save(new Question(99L, "QuestionHeadline", "QuestionDescription", "QuestionResult", new Quiz(1L, "TestQuiz", "for testing purposes")));

        Question question = new Question(100L, "QuestionHeadline", "QuestionDescription", "QuestionResult", new Quiz(2L, "TestQuiz2", "for testing purposes"));
        questionRepository.save(question);

        Table table = new Table(DatasourceFactory.getDataSource(), "Question");
        output(table).toConsole();

        //act
        long expectedRows = table.getRowsList().size() - 1;
        questionRepository.delete(expectedRows);

        table = new Table(DatasourceFactory.getDataSource(), "Question");
        output(table).toConsole();

        //assert
        assertThat(table).hasNumberOfRows((int) expectedRows);
    }

    @Order(3000)
    @Test
    void findAll() {
        //arrange
        QuestionRepository questionRepository = new QuestionRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        //act
        int findAllRows = questionRepository.findAll().size();
        int tableRows = table.getRowsList().size();

        //assert
        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Order(4000)
    @Test
    void findById() {
        //arrange
        QuestionRepository questionRepository = new QuestionRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");
        output(table).toConsole();

        //act
        Question question = questionRepository.findById(1L);

        //assert
        String[] expected = {String.valueOf(question.getQueId()), question.getQueHeadline()};
        String[] actual = {
                table.getRow(0).getValuesList().get(0).getValue().toString(),
                table.getRow(0).getValuesList().get(1).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }
}