import javax.swing.*;
import java.awt.*;



/**
 * This class develops the graphical user interface of the game.
 */
public class GamePanel{
    //Frame


    JFrame panel = new JFrame();

    String []options = {"200","500","700","1000"};

    //Labels
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel labelA = new JLabel();
    JLabel labelB = new JLabel();
    JLabel labelC = new JLabel();
    JLabel labelD = new JLabel();
    JLabel question = new JLabel();
    JLabel question1 = new JLabel();
    //Text fields
    JTextField roundType = new JTextField();
    JTextField category  = new JTextField();




    //Time
    JLabel seconds_left = new JLabel();



    /** Constructor
     *
     */
    public GamePanel() {
        //Basic Game
        panel.setTitle("Buzz");
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(960,950);
        panel.setLocation(400,0);
        panel.getContentPane().setBackground(new Color(50,50,50));
        panel.setLayout(null);
        panel.setResizable(false);
        panel.setFocusable(true);





        //Text field for round type
        roundType.setBounds(0,0,650,30);
        roundType.setBackground(new Color(25,25,25));
        roundType.setForeground(new Color(25, 0, 255, 206));
        roundType.setFont(new Font("Ink Free",Font.BOLD,30));
        roundType.setBorder(BorderFactory.createBevelBorder(1));
        roundType.setHorizontalAlignment(JTextField.CENTER);
        roundType.setEditable(false);

        // Text Field for category
        category .setBounds(0,30,650,50);
        category.setBackground(new Color(25,25,25));
        category.setForeground(new Color(25, 0, 255, 206));
        category.setFont(new Font("Ink Free",Font.BOLD,30));
        category.setBorder(BorderFactory.createBevelBorder(1));
        category.setHorizontalAlignment(JTextField.CENTER);
        category.setEditable(false);
        category.setVisible(true);



        //Label for question
        question.setBounds(0,80,960,40);
        question.setBackground(new Color(25,25,25));
        question.setForeground(new Color(25,255,0));
        question.setFont(new Font("Ink free",Font.BOLD,20));
        question.setBorder(BorderFactory.createBevelBorder(1));

        question1.setVisible(false);
        question1.setBounds(0,120,284,197);




        //Labels
        answer_labelA.setBounds(125,400,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(0,34,255));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,40));

        //Label for answer 2
        answer_labelB.setBounds(125,500,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(0,34,255));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,40));

        //Label for answer 3
        answer_labelC.setBounds(125,600,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(0, 34, 255));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,40));

        //Label for answer 4
        answer_labelD.setBounds(125,700,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(0, 34, 255));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,40));


        labelA.setBounds(0,400,500,100);
        labelA.setBackground(new Color(50,50,50));
        labelA.setForeground(new Color(25,255,0));
        labelA.setFont(new Font("MV Boli",Font.PLAIN,40));
        labelA.setText("A)");

        labelB.setBounds(0,500,500,100);
        labelB.setBackground(new Color(50,50,50));
        labelB.setForeground(new Color(25,255,0));
        labelB.setFont(new Font("MV Boli",Font.PLAIN,40));
        labelB.setText("B)");

        labelC.setBounds(0,600,500,100);
        labelC.setBackground(new Color(50,50,50));
        labelC.setForeground(new Color(25,255,0));
        labelC.setFont(new Font("MV Boli",Font.PLAIN,40));
        labelC.setText("C)");

        labelD.setBounds(0,700,500,100);
        labelD.setBackground(new Color(50,50,50));
        labelD.setForeground(new Color(25,255,0));
        labelD.setFont(new Font("MV Boli",Font.PLAIN,40));
        labelD.setText("D)");




        //time label
        seconds_left.setBounds(710,500,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setVisible(false);







        panel.add(question1);
        panel.add(seconds_left);
        panel.add(category);
        panel.add(question);
        panel.add(roundType);
        panel.add(labelA);
        panel.add(labelB);
        panel.add(labelC);
        panel.add(labelD);
        panel.add(answer_labelA);
        panel.add(answer_labelB);
        panel.add(answer_labelC);
        panel.add(answer_labelD);


        panel.setVisible(true);
    }

    /**
     *  This method changes roundType label text depend on which round type is it.
     * @param round_num round number.
     */
    public void round_type(int round_num) {
        if(round_num == 0)
        {
            roundType.setText("Round Type :Right Answer");
        }
        else if(round_num ==1)
        {
            roundType.setText("Round Type: Countdown");
            seconds_left.setVisible(true);

        }
        else if(round_num ==2){
            roundType.setText("Round Type : Betting");
        }
        else if(round_num ==3){
            roundType.setText("Round Type: Quick Answer");


        }
        else if(round_num ==4){
            roundType.setText("Round Type: Thermometer");

        }
    }

    /**
     * This method created a window to allow player give a bet.
     * @return value of bet player ordered.
     */
    public int getBet() {
        //not visible till player gives his bet.
        answer_labelA.setVisible(false);
        answer_labelB.setVisible(false);
        answer_labelC.setVisible(false);
        answer_labelD.setVisible(false);
        labelA.setVisible(false);
        labelB.setVisible(false);
        labelC.setVisible(false);
        labelD.setVisible(false);
        question.setVisible(false);
        // bet for player.
        int value = JOptionPane.showOptionDialog(null, "Give your bet",
                "Bet", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);
        String a = String.valueOf(value);
        if (value == JOptionPane.CLOSED_OPTION) {
            return 0;
        }
        answer_labelA.setVisible(true);
        answer_labelB.setVisible(true);
        answer_labelC.setVisible(true);
        answer_labelD.setVisible(true);
        labelA.setVisible(true);
        labelB.setVisible(true);
        labelC.setVisible(true);
        labelD.setVisible(true);
        question.setVisible(true);
        if (a.equals("0"))
            value = 200;
        if (a.equals("1"))
            value = 500;
        if (a.equals("2"))
            value = 700;
        if (a.equals("3"))
            value = 1000;
        return value;
    }
}