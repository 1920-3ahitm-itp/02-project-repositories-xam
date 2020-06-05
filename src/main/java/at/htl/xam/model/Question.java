package at.htl.xam.model;

public class Question {

    private Long queId;
    private String queHeadline;
    private String queDesc;
    private String queResult;
    private Quiz queQuiz;


    public Question() {
    }

    public Question(Long queId, String queHeadline, String queDesc, String queResult) {
        this.queId = queId;
        this.queHeadline = queHeadline;
        this.queDesc = queDesc;
        this.queResult = queResult;
    }

    public Question(String queHeadline, String queDesc, String queResult) {
        this.queHeadline = queHeadline;
        this.queDesc = queDesc;
        this.queResult = queResult;
    }

    public Long getQueId() {
        return queId;
    }

    public void setQueId(Long queId) {
        this.queId = queId;
    }

    public String getQueHeadline() {
        return queHeadline;
    }

    public void setQueHeadline(String queHeadline) {
        this.queHeadline = queHeadline;
    }

    public String getQueDesc() {
        return queDesc;
    }

    public void setQueDesc(String queDesc) {
        this.queDesc = queDesc;
    }

    public String getQueResult() {
        return queResult;
    }

    public void setQueResult(String queResult) {
        this.queResult = queResult;
    }

    public Quiz getQueQuiz() {
        return queQuiz;
    }

    public void setQueQuiz(Quiz queQuiz) {
        this.queQuiz = queQuiz;
    }
}