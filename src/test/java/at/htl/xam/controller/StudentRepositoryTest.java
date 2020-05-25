package at.htl.xam.controller;

import at.htl.xam.model.Student;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

class StudentRepositoryTest {

    @Test
    void insert() {
        StudentRepository studentRepository = new StudentRepository();

        Student student = new Student("StudentName", "StudentUsername", "StudentPassword", "StudentClassroom");

        Table table = new Table(DatasourceFactory.getDataSource(), "Student");
        studentRepository.save(student);

        Assertions.assertThat(table).row(table.getRowsList().size() - 1)
                .value("name").isEqualTo("StudentName");
    }

    @Test
    void delete() {
        StudentRepository studentRepository = new StudentRepository();

        studentRepository.save(new Student(99L, "StudentName", "StudentUsername", "StudentPassword", "StudentClassroom"));

        Student student = new Student(99L, "StudentName", "StudentUsername", "StudentPassword", "StudentClassroom");
        studentRepository.save(student);
        Table table = new Table(DatasourceFactory.getDataSource(), "Student");

        int rowsBefore = table.getRowsList().size();
        studentRepository.delete(rowsBefore - 1L);
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore).isEqualTo(rowsAfter);
    }

    @Test
    void findAll() {
        StudentRepository studentRepository = new StudentRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Student");

        int findAllRows = studentRepository.findAll().size();
        int tableRows = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Test
    void findById() {
        StudentRepository studentRepository = new StudentRepository();
        Table table = new Table(DatasourceFactory.getDataSource(), "Student");

        Student student = studentRepository.findById(1L);

        String[] expected = {String.valueOf(student.getsId()), student.getsName(), student.getsUsername(), student.getsPassword(), student.getsClassRoom()};
        String[] actual = {
                table.getRow(1).getValuesList().get(0).getValue().toString(),
                table.getRow(1).getValuesList().get(1).getValue().toString(),
                table.getRow(1).getValuesList().get(2).getValue().toString(),
                table.getRow(1).getValuesList().get(3).getValue().toString()
        };

        org.assertj.core.api.Assertions.assertThat(expected).isEqualTo(actual);
    }
}