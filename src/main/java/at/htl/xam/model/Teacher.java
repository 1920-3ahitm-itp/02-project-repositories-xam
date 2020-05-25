package at.htl.xam.model;

public class Teacher{

    private Long tId;
    private String tName;
    private String tUsername;
    private String tPassword;

    public Teacher() {
    }

    public Teacher(Long tId, String tName, String tUsername, String tPassword) {
        this.tId = tId;
        this.tName = tName;
        this.tUsername = tUsername;
        this.tPassword = tPassword;
    }

    public Long gettId() {
        return tId;
    }

    public String gettName() {
        return tName;
    }

    public String gettUsername() {
        return tUsername;
    }

    public String gettPassword() {
        return tPassword;
    }



}
