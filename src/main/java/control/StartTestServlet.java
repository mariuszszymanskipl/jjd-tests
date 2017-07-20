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
import java.util.*;

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
    private int testSize;
    private HttpSession session;
    private Map<Question, List<Character>> studentAnswersMap;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        studentAnswersMap = new HashMap<>();
        session = request.getSession();
        session.setAttribute("numberOfCorrectAnswers", 0);

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("characters", characters);
        questionsStore = qaService.readJson();
        counter = 0;
        testSize = questionsStore.getQuestions().size();
        Question question1 = questionsStore.getQuestions().get(counter);
        request.setAttribute("question", question1);
        request.setAttribute("testSize", testSize);
        request.setAttribute("questionNumber", question1.getQuestionId());
        request.setAttribute("buttonName", "Next question");
        request.getRequestDispatcher("startTest.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Question questionToVerify = questionsStore.getQuestions().get(counter);
        Integer numberOfCorrectAnswers = (Integer) session.getAttribute("numberOfCorrectAnswers");
        List<Character> correctAnswers = questionToVerify.getCorrectAnswers();
        List<Character> studentAnswers = this.getStudentAnswers(request);
        boolean answerIsCorrect = verifyCorrectAnswers(studentAnswers, correctAnswers);
        if (answerIsCorrect) {
            numberOfCorrectAnswers++;
            session.setAttribute("numberOfCorrectAnswers", numberOfCorrectAnswers);
        }
        studentAnswersMap.put(questionToVerify, studentAnswers);

        if (counter == testSize - 1) { // last question request
            float percentageResult = ((float)numberOfCorrectAnswers / testSize) * 100;
            String formattedResult = String.format("%.02f", percentageResult);
            request.setAttribute("result", numberOfCorrectAnswers);
            request.setAttribute("testSize", testSize);
            request.setAttribute("percentageResult", formattedResult);
            request.setAttribute("studentAnswersMap", studentAnswersMap);
            request.getRequestDispatcher("finishTest.jsp").forward(request, response);
        }
        if (counter < testSize - 1) {
            counter++;
            Question nextQuestion = questionsStore.getQuestions().get(counter);
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("characters", characters);
            request.setAttribute("question", nextQuestion);
            request.setAttribute("testSize", testSize);
            if (counter < testSize - 1) {
                request.setAttribute("buttonName", "Next question");
            } else {
                request.setAttribute("buttonName", "Finish");
            }
            request.getRequestDispatcher("startTest.jsp").forward(request, response);
        }
    }

    private boolean verifyCorrectAnswers(List<Character> studentAnswers, List<Character> correctAnswers) {
        return studentAnswers.size() == correctAnswers.size() && correctAnswers.containsAll(studentAnswers);
    }

    private List<Character> getStudentAnswers(HttpServletRequest request){
        int numberOfChars = characters.size();
        List<Character> studentAnswers = new ArrayList<>();
        Optional<Object> optionalCorrectAnswer;
        for (int i = 1; i <= numberOfChars; i++) {
            String reqParameter = "correct_" + characters.get(i);
            optionalCorrectAnswer = Optional.ofNullable(request.getParameter(reqParameter));
            optionalCorrectAnswer.ifPresent(o -> studentAnswers.add(o.toString().charAt(0)));
        }
        return studentAnswers;
    }
}
