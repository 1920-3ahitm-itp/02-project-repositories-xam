package at.htl.xam.controller;

import at.htl.xam.database.SqlRunner;
import at.htl.xam.model.Teacher;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TeacherRepositoryTest {

    @BeforeAll
    static void beforeAll() {
        SqlRunner.run();
    }

    @Test
    void insert() {
        TeacherRepository teacherRepository = new TeacherRepository();

        Teacher teacher = new Teacher("TeacherName", "TeacherUsername", "TeacherPassword");

        Table table = new Table(DatasourceFactory.getDataSource(), "Teacher");
        teacherRepository.save(teacher);

        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("name").isEqualTo("TeacherName");
    }

    @Test
    void delete() {
        TeacherRepository teacherRepository = new TeacherRepository();

        teacherRepository.save(new Teacher(99L, "TeacherName", "TeacherUsername", "TeacherPassword"));

        Teacher teacher = new Teacher(99L, "TeacherName", "TeacherUsername", "TeacherPassword");
        teacherRepository.save(teacher);
        Table table = new Table(DatasourceFactory.getDataSource(), "Teacher");

        int rowsBefore = table.getRowsList().size();
        teacherRepository.delete(rowsBefore - 1L);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    void findAll() {
        TeacherRepository teacherRepository = new TeacherRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Teacher");

        int findAllRows = teacherRepository.findAll().size();
        int tableRows = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Test
    void findById() {
        TeacherRepository teacherRepository = new TeacherRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Teacher");

        Teacher teacher = teacherRepository.findById(1L);

        String[] expected = {String.valueOf(teacher.gettId()), teacher.gettName(), teacher.gettUsername(), teacher.gettPassword()};
        String[] actual = {
                table.getRow(1).getValuesList().get(0).getValue().toString(),
                table.getRow(1).getValuesList().get(1).getValue().toString(),
                table.getRow(1).getValuesList().get(2).getValue().toString(),
                table.getRow(1).getValuesList().get(3).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }
}