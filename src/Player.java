import java.io.Serializable;

/**
 * Class that represents a player.
 */

public class Player implements Serializable {
    private long score;
    private int wins;
    private String name;
    private transient String curr_an;
    private transient int cons_answers;

    /**
     * Constructor
     */
    public Player() {
        score = 0;
        name = null;
        curr_an = null;
        cons_answers=0;
        wins=0;

    }

    /**
     * Returns the score of player
     * @return score
     */
    public double getScore() {
        return score;
    }

    /**
     * This method adds points in current score.
     * @param score The score
     */
    public void setScore(double score) {this.score+=score; }

    /** This method changes name of player.
     *
     * @param a new name
     */
    public void setName(String a ) {
        name = a;
    }

    /**
     *
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * This method changes number of consecutive right answers of player.
     */
    public void setCons_answers(int a) { cons_answers+=a;}

    /**
     *
     * @return number of consecutive right answers.
     */
    public int getCons_answers() {
        return cons_answers;
    }

    /**
     * This method changes the current answer of a player in a certain question.
     */
    public void setCurr_answer(String a){ curr_an=a;}

    /**
     *
     * @return current answer of player.
     */
    public String getCurr_an() {return curr_an;}

    /**
     *  method adds a win to player.
     */
    public void setWins() {
        this.wins++;
    }

    /**
     *
     * @return wins of player.
     */
    public int getWins () {
        return wins;
    }

    /**
     *
     * @return string with wins and name of player
     */
    public String toWins() {
        return "Wins:"+this.wins+":"+this.name;
    }

    /**
     *
     * @return string with score and name of player
     */
    public String toScore() {
        return "Score:"+ this.score+":"+this.name;
    }
}