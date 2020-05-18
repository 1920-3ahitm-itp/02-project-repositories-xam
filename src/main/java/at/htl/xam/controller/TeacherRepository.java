package at.htl.xam.controller;

import at.htl.xam.model.Question;
import at.htl.xam.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    private TeacherRepository teacherRepository = new TeacherRepository();

    public TeacherRepository() {
        if (!tableExists()) {
            createTable();
        }
    }

    public void createTable() {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "CREATE TABLE Teacher(" +
                    "teacher_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    "name varchar(50) NOT NULL," +
                    "username varchar(50) NOT NULL," +
                    "password varchar(32) NOT NULL," +
                    "CONSTRAINT PK_Teacher PRIMARY KEY (teacher_id)" +
                    ")";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            System.out.println("Created table Teacher.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists() {
        try (Connection conn = DatasourceFactory.getDataSource().getConnection()) {

            String sql = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = 'Teacher'";
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


    public void addTeacher(Teacher teacher) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Insert teacher in database
            String sql = "INSERT INTO Teacher(name, username, password) VALUES(?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getUsername());
            pstmt.setString(3, teacher.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.getAllTeachers();

        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Select all teachers from database
            String sql = "SELECT teacher_id, name, username, password FROM Teacher";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                long id = result.getInt("question_id");
                String name = result.getString("name");
                String username = result.getString("username");
                String password = result.getString("password");
                teachers.add(new Teacher(id, name, username, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }


    public void removeTeacher(Teacher teacher) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Delete teacher from database
            String sql = "DELETE FROM Teacher WHERE teacher_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, teacher.getId());

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveTeacher(Teacher teacher) {
        try (Connection connection = DatasourceFactory.getDataSource().getConnection()) {

            // TODO: Update question in database
            String sql = "UPDATE Teacher SET teacher_id = ?, name = ?, username = ?, password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, teacher.getId());
            pstmt.setString(2, teacher.getName());
            pstmt.setString(3, teacher.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
