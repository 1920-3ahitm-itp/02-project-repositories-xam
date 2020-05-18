package at.htl.xam.controller;

import at.htl.xam.model.Question;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {

    @Test
    void save() {
        QuestionRepository questionRepository = new QuestionRepository();

        Question question = new Question(1L,"QuestionHeadline", "QuestionDescription", "QuestionResult");
        questionRepository.saveQuestion(question);

        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        Assertions.assertThat(table).row(0)
                .value("headline").isEqualTo("QuestionHeadline")
                .value("description").isEqualTo("QuestionDescription")
                .value("result").isEqualTo("QuestionResult");
    }

    @Test
    void insert() {
        QuestionRepository questionRepository = new QuestionRepository();

        Question question = new Question("QuestionHeadline", "QuestionDescription", "QuestionResult");

        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        int rowsBefore = table.getRowsList().size();
        questionRepository.addQuestion(question);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    void delete() {
        QuestionRepository questionRepository = new QuestionRepository();

        questionRepository.addQuestion(new Question(99L, "QuestionHeadline", "QuestionDescription", "QuestionResult"));

        Question question = new Question(99L, "QuestionHeadline", "QuestionDescription", "QuestionResult");
        questionRepository.addQuestion(question);
        Table table = new Table(DatasourceFactory.getDataSource(), "Question");

        int rowsBefore = table.getRowsList().size();
        questionRepository.removeQuestion(rowsBefore - 1);
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

        Question question = questionRepository.findById(2);

        String[] expected = {String.valueOf(question.getId()), question.getHeadline(), question.getDesc(), question.getResult()};
        String[] actual = {
                table.getRow(1).getValuesList().get(0).getValue().toString(),
                table.getRow(1).getValuesList().get(1).getValue().toString(),
                table.getRow(1).getValuesList().get(2).getValue().toString(),
                table.getRow(1).getValuesList().get(3).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }
}