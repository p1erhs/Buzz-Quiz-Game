
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.ZonedDateTime;

import java.util.Random;

/**
 * That class extends class "GamePanel" which is the class with the basic frame and does the build of the game.
 */
public class Game extends GamePanel {

    int bet;
    int bet1;

    int[] played;
    long mil;

    Random rand;
    //Timer for round type Countdown.
    long millis;
    int count = 5;
    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (count == 0) {
                timer.stop();
                count = 5;
                wrongAns();
                right.restart();
            }
            if (count == 5) {
                millis = ZonedDateTime.now().toInstant().toEpochMilli();

            }
            seconds_left.setText(String.valueOf(count));
            count--;

        }
    });
    //Timer to wait 1 sec and display next question.
    Timer right = new Timer(1000, new ActionListener() {
        int seconds = 1;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (seconds == 0) {
                right.stop();
                timer.stop();
                changeQuestion();

                seconds = 1;


            }

            seconds--;

        }
    });

    private Questionlist quest;
    private String[] shuffled;

    private int allow = 0; //variable just to count how many times player pressed key.
    private int allow1 = 0;
    private final Player player1;
    private final Player player2;
    private final int players_num;
    private final String[] names;

    private String right_answer;
    //round type number
    private int round_num;
    //counter variable to display the (j)st question.
    int j = 0;


    //variable that increase 5 for every round
    int quest_displayed = 5;


    /**
     * Constructor
     * @param players_num players number.
     * @param names names of players.
     */
    public Game(int players_num, String[] names) {

        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (j <20) {
                    super.keyTyped(e);
                    switch (e.getKeyChar()) {
                        case 'a':
                            player1.setCurr_answer(answer_labelA.getText());
                            break;
                        case 's':
                            player1.setCurr_answer(answer_labelB.getText());
                            break;
                        case 'd':
                            player1.setCurr_answer(answer_labelC.getText());
                            break;
                        case 'f':
                            player1.setCurr_answer(answer_labelD.getText());
                            break;
                        case 'h':
                            player2.setCurr_answer(answer_labelA.getText());
                            break;
                        case 'j':
                            player2.setCurr_answer(answer_labelB.getText());
                            break;
                        case 'k':
                            player2.setCurr_answer(answer_labelC.getText());
                            break;
                        case 'l':
                            player2.setCurr_answer(answer_labelD.getText());
                            break;
                    }
                    if (e.getKeyChar() == 'a' || e.getKeyChar() == 's' || e.getKeyChar() == 'd' || e.getKeyChar() == 'f') {
                        allow++; // increasing every time player presses his key
                        played[0] = 1;

                        if (player1.getCurr_an().equals(right_answer) && allow == j + 1) {
                            if (round_num == 0)
                                player1.setScore(1000);
                            if (round_num == 1) {
                                mil = ZonedDateTime.now().toInstant().toEpochMilli();
                                double m = 5000 - (mil - millis);
                                player1.setScore(m * 0.2);
                            }
                            if (round_num == 2) {
                                player1.setScore(bet);
                            }
                            if (round_num == 3 && played[1] == 1)
                                player1.setScore(500);
                            else if (round_num == 3 && played[1] == 0)
                                player1.setScore(1000);
                            if (round_num == 4)
                                player1.setCons_answers(1);
                        } else if (allow == j + 1) {
                            player1.setScore(-bet);
                        }
                    } else if ((e.getKeyChar() == 'h' || e.getKeyChar() == 'j' || e.getKeyChar() == 'k' || e.getKeyChar() == 'l') && (players_num == 2)) {
                        allow1++;
                        played[1] = 1;


                        if (player2.getCurr_an().equals(right_answer) && allow1 == j + 1) {
                            if (round_num == 0)
                                player2.setScore(1000);
                            if (round_num == 1) {
                                mil = ZonedDateTime.now().toInstant().toEpochMilli();
                                double m = 5000 - (mil - millis);
                                player2.setScore(m * 0.2);
                            }
                            if (round_num == 2) {
                                player2.setScore(bet1);
                            }
                            if (round_num == 3 && played[0] == 1)
                                player2.setScore(500);
                            else if (round_num == 3 && played[0] == 0)
                                player2.setScore(1000);
                            if (round_num == 4)
                                player2.setCons_answers(1);
                        } else if (allow1 == j + 1) {
                            player2.setScore(-bet1);
                        }
                    }
                    if ((players_num == 1 && played[0] == 1) || (players_num == 2 && played[0] == 1 && played[1] == 1)) {
                        wrongAns();
                        right.restart();
                    }


                }
            }
        });


        shuffled = new String[4];
        player1 = new Player();
        player2 = new Player();
        this.players_num = players_num;
        this.names = names;
        played = new int[2];


        rand = new Random();

        round_num = getRound_num();
        play();
    }

    /**
     * This method starts game with first question.
     */
    public void play() {
        played[0] = 0; //this array let us know if player1 played.if value=0 then he didnt played.
        played[1] = 0; //same for player2.
        addPlayer(players_num, names);

        quest = new Questionlist(); // creating new Question list.

        round_type(round_num);  // getting round type number.


        roundSet();
        if(j<20)
            setQuest();

    }


    /**
     * This method changes names of players.
     *
     * @param players_sum Number of players.
     * @param names       Names of players.
     */
    public void addPlayer(int players_sum, String[] names) {
        player1.setName(names[0]);
        if (players_sum == 2)
            player2.setName(names[1]);
    }

    /**
     * This method returns the Type of round number.
     */
    public int getRound_num() {
        if (players_num == 1)
            return rand.nextInt(3);
        else
            return rand.nextInt(5); //if round type is "Quick Answer" or "Thermometer" it makes senses with 2 players only.
    }

    /**
     * This method adds the score for player that answered 5 consecutive questions right.
     */
    public void thermometer() {
        if (player1.getCons_answers() == 5) {
            player1.setScore(5000);
            round_num = getRound_num();
        }
        if ((player2.getCons_answers() == 5) && (players_num == 2)) {
            player2.setScore(5000);
            round_num = getRound_num();
        }
    }

    /**
     * This method refreshes GUI with new questions
     */
    public void changeQuestion() {

        played[0] = 0;
        played[1] = 0;


        j += 1; // increase variable j to display next jst question,answer etc...
        allow = j;
        allow1 = j;

        roundSet();
        if(j<20)
            setQuest();
    }

    /**
     * This method changes the colour of wrong answers to red just to update players which is the right answer.
     */
    public void wrongAns() {
        if (!answer_labelA.getText().equals(right_answer))
            labelA.setForeground(new Color(255, 0, 0, 255));
        if (!answer_labelB.getText().equals(right_answer))
            labelB.setForeground(new Color(255, 0, 0, 255));
        if (!answer_labelC.getText().equals(right_answer))
            labelC.setForeground(new Color(255, 0, 0, 255));
        if (!answer_labelD.getText().equals(right_answer))
            labelD.setForeground(new Color(255, 0, 0, 255));
    }

    /**
     * This method is used to make changes depending on current round type.
     */
    public void roundSet() {
        if (round_num == 4) {
            thermometer();
            //every round has 5 questions.If thermometer is not completed and questions are over then refreshing questions again.
            if (((players_num == 2 && player1.getCons_answers() != 5 && player2.getCons_answers() != 5) || (players_num == 1 && player1.getCons_answers() != 5)) &&
                    j == 20) {
                j-=5;
                quest = new Questionlist();
            }
            if(j==quest_displayed)
                quest_displayed+=5;
            if((players_num==1 && player1.getCons_answers()==5) || (players_num==2 && (player1.getCons_answers()==5 || player2.getCons_answers()==5))){
                player1.setCons_answers(-player1.getCons_answers());
                player2.setCons_answers(-player2.getCons_answers());
                round_num = getRound_num();
                round_type(round_num);
            }
        }
        if ((j == quest_displayed && round_num!=4) || (j == 2 * quest_displayed && round_num == 2 && players_num == 2)) {
            //round type "bet" has 5 questions for each player if players number is 2.
            quest_displayed += 5;
            round_num = getRound_num();
            round_type(round_num);
        }
        if (round_num == 1) {
            seconds_left.setVisible(true);
            count = 5;
            timer.restart();
        } else {
            timer.stop(); //we need timer only for round type countdown else is redundant.
            seconds_left.setVisible(false);
        }

        if (j == 20) {
            timer.stop();
            //panel.dispose();
            results(); //displaying results;
            HighScore a = new HighScore(1, player1);
            if (players_num == 1) {
                a = new HighScore(1, player1);
            }
            if (players_num == 2) {
                if (player1.getScore() > player2.getScore()) {
                    a = new HighScore(2, player1);

                }
                if (player2.getScore() > player1.getScore()) {
                    a = new HighScore(2, player2);

                }
            }
            a.readFile(); //reading file.
            a.writeFile(); //writing file.
            panel.add(a.next); // adding next button to frame.
            a.next.setVisible(true);
        }
        if (j <20) {
            category.setText("Category : " + quest.questions.get(j).getCategory()); //category.
            if (round_num == 2) {
                bet = getBet();
                if (players_num == 2)
                    bet1 = getBet();
            }
        }
    }

    /**
     * This method changes labels with a new question.
     */
    public void setQuest() {
        labelA.setForeground(new Color(25, 255, 0));
        labelB.setForeground(new Color(25, 255, 0));
        labelC.setForeground(new Color(25, 255, 0));
        labelD.setForeground(new Color(25, 255, 0));
        question.setText(quest.questions.get(j).getDescription());// question.
        if (quest.questions.get(j).getHasImage()) {
            question1.setIcon(quest.questions.get(j).getUrl());
            question1.setVisible(true);
        } else
            question1.setVisible(false);
        right_answer = quest.questions.get(j).getRight_Answer(); //right answer.
        shuffled = quest.questions.get(j).getAnswers_shuff(); // shuffled answers.
        answer_labelA.setText(shuffled[0]);
        answer_labelB.setText(shuffled[1]);
        answer_labelC.setText(shuffled[2]);
        answer_labelD.setText(shuffled[3]);
    }

    /**
     * This method displays final results that is the names and scores of players that played.
     */
    public void results() {
        labelA.setVisible(false);
        labelB.setVisible(false);
        labelC.setVisible(false);
        labelD.setVisible(false);
        answer_labelB.setVisible(false);
        answer_labelC.setVisible(false);
        answer_labelD.setVisible(false);
        answer_labelA.setText(player1.getName() + ":" + player1.getScore() + " points");
        if (players_num == 2) {
            answer_labelB.setVisible(true);
            answer_labelB.setText(player2.getName() + ":" + player2.getScore() + " points");
        }

    }
}


