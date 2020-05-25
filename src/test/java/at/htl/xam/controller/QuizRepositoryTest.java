package at.htl.xam.controller;

import at.htl.xam.model.Question;
import at.htl.xam.model.Quiz;
import at.htl.xam.model.Teacher;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizRepositoryTest {

    @Test
    void save() {
        QuizRepository quizRepository = new QuizRepository();

        Quiz quiz = new Quiz(10L, "quiName",  "quiDescription", new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword"));
        quizRepository.save(quiz);

        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");

        Assertions.assertThat(table).row(0)
                .value("quiName").isEqualTo("quiName")
                .value("quiName").isEqualTo("quiName")
                .value("quiTeacher_id").isEqualTo("teacherId");
    }

    @Test
    void delete() {
        QuizRepository quizRepository = new QuizRepository();

        quizRepository.save(new Quiz(99L, "quiName", "quiDescription", new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword")));

        Quiz quiz = new Quiz(99L, "quiName",  "quiDescription", new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword"));
        Teacher teacher = new Teacher(1L, "teacherName", "teacherUsername", "teacherPassword");
        quizRepository.save(quiz);
        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");

        int rowsBefore = table.getRowsList().size();
        quizRepository.delete(rowsBefore - 1L);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    void findAll() {
        QuizRepository quizRepository = new QuizRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");

        int findAllRows = quizRepository.findAll().size();
        int tableRows = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Test
    void findById() {
        QuizRepository quizRepository = new QuizRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Quiz");

        Quiz quiz = quizRepository.findById(1L);

        String[] expected = {String.valueOf(quiz.getQuiId()), quiz.getQuiName(), quiz.getQuiDescription(), String.valueOf(quiz.getQuiTeacher().gettId())};
        String[] actual = {
                table.getRow(1).getValuesList().get(0).getValue().toString(),
                table.getRow(1).getValuesList().get(1).getValue().toString(),
                table.getRow(1).getValuesList().get(2).getValue().toString(),
                table.getRow(1).getValuesList().get(3).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }

}