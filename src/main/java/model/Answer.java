package model;

/**
 * @author Mariusz Szymanski
 */
public class Answer {

    private char answerId;
    private String answerText;

    public char getAnswerId() {
        return answerId;
    }

    public void setAnswerId(char answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
