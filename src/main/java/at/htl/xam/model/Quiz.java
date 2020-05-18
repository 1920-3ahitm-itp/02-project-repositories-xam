package at.htl.xam.model;

public class Quiz {

    private Long id;
    private String name;
    private String description;
    private Long teacher_id;


    public Quiz() {
    }

    public Quiz(Long id, String name, String description, Long teacher_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacher_id = teacher_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
