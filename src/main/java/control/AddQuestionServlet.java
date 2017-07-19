package control;

import model.Answer;
import model.Question;
import model.QuestionsStore;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author Mariusz Szymanski
 */
@WebServlet(name = "AddQuestionServlet", urlPatterns = "addQuestion")
public class AddQuestionServlet extends HttpServlet {

    @EJB
    private QuestionsStore questionsStore;
    private QAService qaService = new QAService();
    private final Map<Integer,Character> characters = qaService.getAnswerCharacters();
    private final List<String> categories = qaService.getCategories();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        questionsStore = qaService.readJson();
        request.setAttribute("characters", characters);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("addQuestion.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Question question = makeQuestionFromParameters(request);
        questionsStore.addQuestion(question);
        qaService.writeJson(questionsStore);
        request.setAttribute("characters", characters);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("addQuestion.jsp").forward(request,response);
    }

    private Question makeQuestionFromParameters(HttpServletRequest request){

        int numberOfChars = characters.size();

        Question question = new Question();
        question.setQuestionId(request.getParameter("questionId"));
        question.setCategory(request.getParameter("category"));
        question.setQuestionText(request.getParameter("questionText"));

        List<Answer> answers = new ArrayList<>();
        Optional<Object> optionalAnswer;
        for (int i=1; i <= numberOfChars; i++) {
            optionalAnswer = Optional.ofNullable(request.getParameter("answer_" + i));
            Integer key = i;
            optionalAnswer.ifPresent(o -> {
                String answerText = o.toString();
                if (!answerText.equals("")) {
                    Answer answer = new Answer();
                    answer.setAnswerText(answerText);
                    answer.setAnswerId(characters.get(key));
                    answers.add(answer);
                }
            });
        }
        question.setAnswers(answers);

        List<Character> correctAnswers = new ArrayList<>();
        Optional<Object> optionalCorrectAnswer;
        for (int i=1; i <= numberOfChars; i++) {
            optionalCorrectAnswer = Optional.ofNullable(request.getParameter("correct_" + i));
            optionalCorrectAnswer.ifPresent(o -> correctAnswers.add(o.toString().charAt(0)));
        }
        question.setCorrectAnswers(correctAnswers);

        return question;
    }
}
