package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Answers {

    private List<Answer> answers = new ArrayList<>();
    private char[] correctAnswers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public char[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(char[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
