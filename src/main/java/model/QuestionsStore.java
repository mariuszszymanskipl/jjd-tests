package model;

import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
@Stateless
public class QuestionsStore implements Serializable{

    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
