import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Class that represents a Question with its description,answer,category etc...
 */
public class Question {
    private String right_Answer;
    private String[] answers = new String[4];
    private String description;
    private String category;
    private int x = -1;
    private ImageIcon url = null;
    private boolean hasImage = false;

    /**
     * @return the right answer.
     */
    public String getRight_Answer() {
        return right_Answer;
    }

    /**
     * This method returns answer in potition z.
     */
    public String getAnswers(int z){
        return answers[z];
    }

    /**
     * This method changes right answer of a question.
     */
    public void setRight_Answer(String right_Answer){
        this.right_Answer = right_Answer;
    }

    /**
     * This method changes category of a question.
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     * This method changes decription of a question.
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * This method initializes 1 answer of total 4 of a question.
     *
     */
    public void setAnswers(String a){
        answers[++x] = a;
    }

    /**
     * @return category of question.
     */
    public String getCategory(){
        return category;
    }

    /**
     * @return description of a question.
     */
    public String getDescription(){
        return description;
    }

    /**
     * This method creates a list with the elements of array with answers in shuffled potitions..
     * @return converted array with shuffled elements.
     */
    public String[] getAnswers_shuff() {
        // new list.
        List<String> answersList = Arrays.asList(answers);
        Collections.shuffle(answersList); // shuffling list.
        String[] shuffledAnswers = new String[4];
        answersList.toArray(shuffledAnswers); // converting in to string array.
        return shuffledAnswers;
    }

    /**
     * This method changes icon of a question.
     */
    public void setUrl(ImageIcon url) {
        this.url=url;
    }

    /**
     * This method returns icon of a question.
     * @return image icon.
     */
    public ImageIcon getUrl() {
        return url;
    }

    /**
     * This method field hasImage with true or false.

     */
    public void setHasImage(boolean flag) {
        hasImage=flag;
    }

    /**
     * This method returns hasImage status.
     */
    public boolean getHasImage() {
        return hasImage;
    }
}
