package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Answers {

    private List<Answer> answers = new ArrayList<>();
    private List<Character> correctAnswers = new ArrayList<>();

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
