package at.htl.xam.model;

public class Student{

    long id;
    String username;
    String password;
    String classRoom;

    public Student() {
    }

    public Student(long id, String username, String password, String classRoom) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.classRoom = classRoom;
    }

    public long getId() {
        return id;
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


