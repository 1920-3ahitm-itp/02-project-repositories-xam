package at.htl.xam.controller;

import at.htl.xam.database.SqlRunner;
import at.htl.xam.model.Question;
import at.htl.xam.model.Quiz;
import at.htl.xam.model.Teacher;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

class QuizRepositoryTest {

    @BeforeEach
    void beforeEach() {
        SqlRunner.run();
    }

    @Order(1000)
    @Test
    void save() {
        //arrange
        QuizRepository quizRepository = new QuizRepository();
        Quiz quiz = new Quiz(10L, "quiName",  "quiDescription", new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword"));
        Table t = new Table(DatasourceFactory.getDataSource(), "Quiz");
        output(t).toConsole();

        //act
        quizRepository.save(quiz);
        t = new Table(DatasourceFactory.getDataSource(), "Quiz");
        output(t).toConsole();

        //assert
        assertThat(t).row(0)
                .row(t.getRowsList().size() - 1)
                .value("NAME").isEqualTo("quiName");
    }

    @Test
    void delete() {
        //arrange
        QuizRepository quizRepository = new QuizRepository();
        quizRepository.save(new Quiz(99L, "quiName", "quiDescription", new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword")));

        Quiz quiz = new Quiz(100L, "quiName",  "quiDescription", new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword"));
        quizRepository.save(quiz);

        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");
        output(table).toConsole();

        //act
        long expected = table.getRowsList().size() - 1;
        quizRepository.delete(expected);

        table = new Table(DatasourceFactory.getDataSource(), "Quiz");
        output(table).toConsole();

        //assert
        assertThat(table).hasNumberOfRows((int) expected);

    }

    @Test
    void findAll() {
        //arrange
        QuizRepository quizRepository = new QuizRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");
        output(table).toConsole();
        int tableRows = table.getRowsList().size();

        //act
        int findAllRows = quizRepository.findAll().size();

        //assert
        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Test
    void findById() {
        //arrange
        QuizRepository quizRepository = new QuizRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");
        output(table).toConsole();

        //act
        Quiz quiz = quizRepository.findById(1L);

        String[] expected = {String.valueOf(quiz.getQuiId()), quiz.getQuiName()};
        String[] actual = {
                table.getRow(0).getValuesList().get(0).getValue().toString(),
                table.getRow(0).getValuesList().get(1).getValue().toString()
        };

        //assert
        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }

}