package at.htl.xam.controller;

import at.htl.xam.model.Question;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import static org.assertj.db.api.Assertions.assertThat;

class QuestionRepositoryTest {

    @Test
    void insert() {
        QuestionRepository questionRepository = new QuestionRepository();

        Question question = new Question("QuestionHeadline", "QuestionDescription", "QuestionResult");

        Table table = new Table(DatasourceFactory.getDataSource(), "Question");
        questionRepository.save(question);

        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("headline").isEqualTo("QuestionHeadline");
    }

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

    @Test
    void findAll() {
        QuestionRepository questionRepository = new QuestionRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        int findAllRows = questionRepository.findAll().size();
        int tableRows = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

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