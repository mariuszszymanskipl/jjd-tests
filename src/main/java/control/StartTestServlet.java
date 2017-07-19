package control;

import model.Question;
import model.QuestionsStore;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private HttpSession session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        session.setAttribute("numberOfCorrectAnswers", 0);

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

        Integer numberOfCorrectAnswers = (Integer) session.getAttribute("numberOfCorrectAnswers");
        List<Character> correctAnswers = questionsStore.getQuestions().get(counter).getCorrectAnswers();
        boolean answerIsCorrect = verifyCorrectAnswers(request, correctAnswers);
        if (answerIsCorrect) {
            numberOfCorrectAnswers++;
            session.setAttribute("numberOfCorrectAnswers", numberOfCorrectAnswers);
        }


        int testSize = questionsStore.getQuestions().size();
        if (counter == testSize - 1) { // last question request
            request.setAttribute("result", numberOfCorrectAnswers);
            request.setAttribute("testSize", testSize);
            request.getRequestDispatcher("finishTest.jsp").forward(request, response);
        }
        if (counter < testSize - 1) {
            counter++;
            Question question = questionsStore.getQuestions().get(counter);
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("characters", characters);
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

    private boolean verifyCorrectAnswers(HttpServletRequest request, List<Character> correctAnswers) {
        int numberOfChars = characters.size();
        List<Character> studentAnswers = new ArrayList<>();
        Optional<Object> optionalCorrectAnswer;
        for (int i = 1; i <= numberOfChars; i++) {
            String reqParameter = "correct_" + characters.get(i);
            optionalCorrectAnswer = Optional.ofNullable(request.getParameter(reqParameter));
            optionalCorrectAnswer.ifPresent(o -> studentAnswers.add(o.toString().charAt(0)));
        }
        return studentAnswers.size() == correctAnswers.size() && correctAnswers.containsAll(studentAnswers);
    }
}
