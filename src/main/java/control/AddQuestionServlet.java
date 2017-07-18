package control;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Answer;
import model.Question;
import model.QuestionsStore;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mariusz Szymanski
 */
@WebServlet(name = "AddQuestionServlet", urlPatterns = "addQuestion")
public class AddQuestionServlet extends HttpServlet {

    @EJB
    private QuestionsStore questionsStore;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Question question = new Question();
        question.setQuestionId(request.getParameter("questionId"));
        question.setQuestionText(request.getParameter("questionText"));

        Answer answerA = new Answer();
        List<Answer> answers = new ArrayList<>();
        answerA.setAnswerId('A');
        answerA.setAnswerText(request.getParameter("answerA"));
        answers.add(answerA);
        Answer answerB = new Answer();
        answerB.setAnswerId('B');
        answerB.setAnswerText(request.getParameter("answerB"));
        answers.add(answerB);
        Answer answerC = new Answer();
        answerC.setAnswerId('C');
        answerC.setAnswerText(request.getParameter("answerC"));
        answers.add(answerC);
        question.setAnswers(answers);

        List<Character> correctAnswers = new ArrayList<>();
        Optional<Object> objectOptional = Optional.ofNullable(request.getParameter("correctA"));
        objectOptional.ifPresent(o -> correctAnswers.add(o.toString().charAt(0)));

        objectOptional = Optional.ofNullable(request.getParameter("correctB"));
        objectOptional.ifPresent(o -> correctAnswers.add(o.toString().charAt(0)));

        objectOptional = Optional.ofNullable(request.getParameter("correctC"));
        objectOptional.ifPresent(o -> correctAnswers.add(o.toString().charAt(0)));

        question.setCorrectAnswers(correctAnswers);

        questionsStore.addQuestion(question);

        writeJson(questionsStore);

        request.getRequestDispatcher("addQuestion.jsp").forward(request,response);
    }

    private void writeJson(QuestionsStore questionsStore) {
        ObjectMapper objectMapper = new ObjectMapper();
        String pathToFile = "src/main/resources/questions.json";
        File jsonFile = new File(pathToFile);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, questionsStore);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
