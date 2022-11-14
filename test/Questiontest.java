import junit.framework.TestCase;

public class Questiontest extends TestCase{

    public void testRight_Answer(){
        Question q = new Question();
        q.setRight_Answer("Teo");
        assertEquals("Teo",q.getRight_Answer());
    }

    public void testCategory(){
        Question q = new Question();
        q.setCategory("Sport");
        assertEquals("Sport",q.getCategory());
    }

    public void testDescription(){
        Question q = new Question();
        q.setDescription("Who are you?");
        assertEquals("Who are you?",q.getDescription());
    }

    public void testAnswers(){
        Question q = new Question();
        q.setAnswers("Good");
        q.setAnswers("False");
        assertEquals("Good",q.getAnswers(0));
        assertEquals("False",q.getAnswers(1));
    }

}
