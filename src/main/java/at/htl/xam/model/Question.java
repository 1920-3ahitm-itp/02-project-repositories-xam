package at.htl.xam.model;

public class Question {

    int id;
    String name;
    String desc;
    String result;

    public Question() {
    }

    public Question(int id, String name, String desc, String result) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
