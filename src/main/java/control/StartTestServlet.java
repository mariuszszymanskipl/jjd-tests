package control;

import model.Question;
import model.QuestionsStore;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@WebServlet(name = "StartTestServlet", urlPatterns = "startTest")
public class StartTestServlet extends HttpServlet {

    @EJB
    private QuestionsStore questionsStore;
    private QAService qaService = new QAService();
    private final Map<Integer, Character> characters = qaService.getAnswerCharacters();
    private int counter;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("characters", characters);
        questionsStore = qaService.readJson();
        counter = 0;
        Question question1 = questionsStore.getQuestions().get(counter);
        request.setAttribute("question", question1);
        request.setAttribute("testSize", questionsStore.getQuestions().size());
        request.setAttribute("questionNumber", question1.getQuestionId());
        request.setAttribute("buttonName", "Next question");
        request.getRequestDispatcher("startTest.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("characters", characters);
        counter++;
        int testSize = questionsStore.getQuestions().size();
        Question question = questionsStore.getQuestions().get(counter);
        request.setAttribute("question", question);
        request.setAttribute("testSize", testSize);
        request.setAttribute("questionNumber", question.getQuestionId());
        if (counter < testSize - 1) {
            request.setAttribute("buttonName", "Next question");
        } else {
            request.setAttribute("buttonName", "Finish");
        }
        request.getRequestDispatcher("startTest.jsp").forward(request, response);
    }
}
