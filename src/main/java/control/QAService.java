package control;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.QuestionsStore;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
public class QAService {

    private final Map<Integer,Character> characters;
    private final List<String> categories;
    private final String pathToFile = "src/main/resources/questions.json";
    private File jsonFile = new File(pathToFile);
    private ObjectMapper objectMapper = new ObjectMapper();

    QAService() {
        this.categories = this.setCategories();
        this.characters = this.setAnswersCharacters();
    }

    Map<Integer,Character> getAnswerCharacters(){
        return this.characters;
    }

    List<String> getCategories() {
        return this.categories;
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

    void writeJson(QuestionsStore questionsStore) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, questionsStore);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    QuestionsStore readJson() {
        QuestionsStore questionsStore = new QuestionsStore();
        try {
            questionsStore = objectMapper.readValue(jsonFile, QuestionsStore.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsStore;
    }
}
