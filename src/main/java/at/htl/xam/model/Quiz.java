package at.htl.xam.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private Long quiId;
    private String quiName;
    private String quiDescription;
    private Teacher quiTeacher;
    public static List<Teacher> teachers = new ArrayList<>();


    public Quiz() {
    }

    public Quiz(Long quiId, String quiName, String quiDescription, Teacher quiTeacher) {
        this.quiId = quiId;
        this.quiName = quiName;
        this.quiDescription = quiDescription;
        this.quiTeacher = quiTeacher;
        teachers.add(quiTeacher);
    }

    public Quiz(Long quiId, String quiName, String quiDescription) {
        this.quiId = quiId;
        this.quiName = quiName;
        this.quiDescription = quiDescription;
    }

    public Quiz(String quiName, String quiDescription, Teacher quiTeacher) {
        this.quiName = quiName;
        this.quiDescription = quiDescription;
        this.quiTeacher = quiTeacher;
    }

    public Long getQuiId() {
        return quiId;
    }

    public void setQuiId(Long quiId) {
        this.quiId = quiId;
    }

    public String getQuiName() {
        return quiName;
    }

    public void setQuiName(String quiName) {
        this.quiName = quiName;
    }

    public String getQuiDescription() {
        return quiDescription;
    }

    public Teacher getQuiTeacher() {
        return quiTeacher;
    }

    public void setQuiDescription(String quiDescription) {
        this.quiDescription = quiDescription;
    }
}
