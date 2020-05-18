package at.htl.xam.model;

public class Question {

    Long id;
    String headline;
    String desc;
    String result;

    public Question() {
    }

    public Question(Long id, String headline, String desc, String result) {
        this.id = id;
        this.headline = headline;
        this.desc = desc;
        this.result = result;
    }

    public Question(String headline, String desc, String result) {
        this.headline = headline;
        this.desc = desc;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
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
