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
    private final Map<Integer,Character> characters = this.setAnswersCharacters();
    private final List<String> categories = this.setCategories();
    private final String pathToFile = "src/main/resources/questions.json";
    private File jsonFile = new File(pathToFile);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        questionsStore = this.readJson();
        request.setAttribute("characters", characters);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("addQuestion.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Question question = makeQuestionFromParameters(request);
        questionsStore.addQuestion(question);
        writeJson(questionsStore);
        request.setAttribute("characters", characters);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("addQuestion.jsp").forward(request,response);
    }

    private Question makeQuestionFromParameters(HttpServletRequest request){

        Map<Integer, Character> characterMap = this.setAnswersCharacters();
        int numberOfChars = characterMap.size();

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
                    answer.setAnswerId(characterMap.get(key));
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

    private Map<Integer,Character> setAnswersCharacters() {
        Map<Integer, Character> characterMap = new HashMap<>();
        List<Character> characters = Arrays.asList('A','B','C','D','E','F','G','H');
        characters.forEach(ch -> characterMap.put(characters.indexOf(ch)+1,ch));
        return characterMap;
    }

    private List<String> setCategories(){
        List<String> categories;
        String[] categoriesArray = {"SCRUM", "TOOLS", "JAVA SE", "JAVA 8", "GIT", "MAVEN", "LINUX",
        "DOCKER", "LOGGERS", "JUNIT", "FRONTEND", "CLEAN CODE", "JAVA EE", "SQL", "JSP/HIBERNATE",
        "JBOSS/WILDFLY", "CI/JENKINS", "TDD", "HTTP", "DEBUG", "REST", "SOAP", "DESIGN PATTERNS", "UML"};
        categories = Arrays.asList(categoriesArray);
        return categories;
    }

    private void writeJson(QuestionsStore questionsStore) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, questionsStore);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private QuestionsStore readJson() {
        QuestionsStore questionsStore = new QuestionsStore();
        try {
            questionsStore = objectMapper.readValue(jsonFile, QuestionsStore.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsStore;
    }
}
