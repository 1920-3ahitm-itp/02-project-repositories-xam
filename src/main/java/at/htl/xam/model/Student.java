package at.htl.xam.model;

public class Student{

    Long id;
    String name;
    String username;
    String password;
    String classRoom;

    public Student() {
    }

    public Student(Long id, String name, String username, String password, String classRoom) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.classRoom = classRoom;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}


