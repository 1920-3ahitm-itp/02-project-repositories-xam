package at.htl.xam.model;

public class Student{

    private Long sId;
    private String sName;
    private String sUsername;
    private String sPassword;
    private String sClassRoom;

    public Student() {
    }

    public Student(Long sId, String sName, String sUsername, String sPassword, String sClassRoom) {
        this.sId = sId;
        this.sName = sName;
        this.sUsername = sUsername;
        this.sPassword = sPassword;
        this.sClassRoom = sClassRoom;
    }

    public Long getsId() {
        return sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsClassRoom() {
        return sClassRoom;
    }

    public void setsClassRoom(String sClassRoom) {
        this.sClassRoom = sClassRoom;
    }
}


