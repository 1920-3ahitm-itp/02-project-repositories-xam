package at.htl.xam.model;

public class Teacher extends Person {

    private int id;
    private String name;
    private String username;
    private String password;

    public Teacher() {
    }

    public Teacher(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



}
