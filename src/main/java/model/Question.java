package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Question {

    private String questionId;
    private String category;
    private String questionText;
    private List<Answer> answers = new ArrayList<>();
    private List<Character> correctAnswers = new ArrayList<>();

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Character> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<Character> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
