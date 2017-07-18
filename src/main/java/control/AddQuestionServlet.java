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
import java.util.*;


/**
 * @author Mariusz Szymanski
 */
@WebServlet(name = "AddQuestionServlet", urlPatterns = "addQuestion")
public class AddQuestionServlet extends HttpServlet {

    @EJB
    private QuestionsStore questionsStore;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Question question = makeQuestionFromParameters(request);
        questionsStore.addQuestion(question);
        writeJson(questionsStore);
        request.getRequestDispatcher("addQuestion.jsp").forward(request,response);
    }

    private Question makeQuestionFromParameters(HttpServletRequest request){

        Map<Integer, Character> characterMap = new HashMap<>();
        List<Character> characters = Arrays.asList('A','B','C','D','E','F','G','H','I','J');
        characters.forEach(ch -> characterMap.put(characters.indexOf(ch)+1,ch));

        Question question = new Question();
        question.setQuestionId(request.getParameter("questionId"));
        question.setQuestionText(request.getParameter("questionText"));

        List<Answer> answers = new ArrayList<>();
        Optional<Object> optionalAnswer;
        for (int i=1; i <= 10; i++) {
            optionalAnswer = Optional.ofNullable(request.getParameter("answer_" + i));
            Integer key = i;
            optionalAnswer.ifPresent(o -> {
                Answer answer = new Answer();
                answer.setAnswerText(o.toString());
                answer.setAnswerId(characterMap.get(key));
                answers.add(answer);
            });
        }
        question.setAnswers(answers);

        List<Character> correctAnswers = new ArrayList<>();
        Optional<Object> optionalCorrectAnswer;
        for (int i=1; i <= 10; i++) {
            optionalCorrectAnswer = Optional.ofNullable(request.getParameter("correct_" + i));
            optionalCorrectAnswer.ifPresent(o -> correctAnswers.add(o.toString().charAt(0)));
        }
        question.setCorrectAnswers(correctAnswers);

        return question;
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
