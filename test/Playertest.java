import junit.framework.TestCase;

public class Playertest extends TestCase{
        public void testscore(){
            Player player = new Player();
            player.setScore(1000);
            assertEquals(1000.0,player.getScore());
        }
        public void testname(){
            Player player = new Player();
            player.setName("Teo");
            assertEquals("Teo",player.getName());

        }
        public void testwins(){
            Player player = new Player();
            player.setWins();
            player.setWins();
            assertEquals(2, player.getWins());
        }
        public void testcons_ans(){
            Player player = new Player();
            player.setCons_answers(5);
            assertEquals(5,player.getCons_answers());
            player.setCons_answers(-5);
            assertEquals(0,player.getCons_answers());

        }
        public void testcurr_ans(){
            Player player = new Player();
            player.setCurr_answer("Tipota");
            assertEquals("Tipota",player.getCurr_an());
        }

}
