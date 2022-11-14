import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a list of all questions.
 */
public class Questionlist {

    List<Question> questions;

    /**
     * Constructor.
     */
    public Questionlist() {
        questions = new ArrayList<>();
        setQuestions();
        Collections.shuffle(questions);//shuffling questions in random order.
    }

    /**
     * This method adds a question to arraylist questions.
     * @param q question we want to add.
     */
    public void addQuestion(Question q) {
        questions.add(q);
    }

    /**
     * This method reads a file and initializes Question class fields with the data of file.
     */
    public void setQuestions() {
        try (BufferedReader in = new BufferedReader(new FileReader("q.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                Question question = new Question(); // creating new question.
                String[] elements = line.split("-"); // splitting line with "-" to make easy the separation of questions,answers and category.
                question.setDescription(elements[1]);
                question.setCategory(elements[2]);
                question.setRight_Answer(elements[3]);
                question.setAnswers(elements[3]);
                question.setAnswers(elements[4]);
                question.setAnswers(elements[5]);
                question.setAnswers(elements[6]);
                if(elements[0].equals("IMAGE")) {
                    question.setHasImage(true);
                    ImageIcon icon = new ImageIcon("Images/"+question.getRight_Answer()+".jpg");
                    question.setUrl(icon);
                }

                addQuestion(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
