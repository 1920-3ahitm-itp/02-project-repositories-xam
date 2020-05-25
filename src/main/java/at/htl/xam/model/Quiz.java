package at.htl.xam.model;

public class Quiz {

    private Long quiId;
    private String quiName;
    private String quiDescription;
    private Long quiTeacher_id;


    public Quiz() {
    }

    public Quiz(Long quiId, String quiName, String quiDescription, Long quiTeacher_id) {
        this.quiId = quiId;
        this.quiName = quiName;
        this.quiDescription = quiDescription;
        this.quiTeacher_id = quiTeacher_id;
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

    public Long getQuiTeacher_id() {
        return quiTeacher_id;
    }

    public void setQuiDescription(String quiDescription) {
        this.quiDescription = quiDescription;
    }
}
