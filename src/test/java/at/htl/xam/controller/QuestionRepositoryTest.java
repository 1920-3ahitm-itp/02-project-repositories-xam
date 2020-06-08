package at.htl.xam.controller;

import at.htl.xam.database.SqlRunner;
import at.htl.xam.model.Question;
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
        QuestionRepository questionRepository = new QuestionRepository();

        questionRepository.save(new Question(99L, "QuestionHeadline", "QuestionDescription", "QuestionResult"));

        Question question = new Question(99L, "QuestionHeadline", "QuestionDescription", "QuestionResult");
        questionRepository.save(question);
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        int rowsBefore = table.getRowsList().size();
        questionRepository.delete(rowsBefore - 1L);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Order(3000)
    @Test
    void findAll() {

        QuestionRepository questionRepository = new QuestionRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        int findAllRows = questionRepository.findAll().size();
        int tableRows = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Order(4000)
    @Test
    void findById() {
        QuestionRepository questionRepository = new QuestionRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        Question question = questionRepository.findById(1L);

        String[] expected = {String.valueOf(question.getQueId()), question.getQueHeadline(), question.getQueDesc(), question.getQueResult()};
        String[] actual = {
                table.getRow(1).getValuesList().get(0).getValue().toString(),
                table.getRow(1).getValuesList().get(1).getValue().toString(),
                table.getRow(1).getValuesList().get(2).getValue().toString(),
                table.getRow(1).getValuesList().get(3).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }
}