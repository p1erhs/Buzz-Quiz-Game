import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class develops the basic Menu of the game
 *
 */

public class StartMenu {
    private Game gamePanel;
    //Frame
    private JFrame frame;

    /**
     * Constructor
     */
    public StartMenu() {
        //Frame
        frame = new JFrame();
        frame.setTitle("Buzz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500,100);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);


        //Labels
        JLabel start1 = new JLabel();
        start1.setText("Hello, let's play Buzz!");
        start1.setBounds(125,0,500,100);
        start1.setBackground(new Color(50,50,50));
        start1.setForeground(new Color(76, 0, 255));
        start1.setFont(new Font("MV Boli",Font.PLAIN,35));
        

        JLabel start = new JLabel();
        //start.setBounds(125,80,400,400);
        ImageIcon icon = new ImageIcon("unnamed.png");
        start.setIcon(icon);



        //Buttons
        JButton start_button = new JButton();
        //start_button.setBounds(125,120,80,200);
        start_button.setText("Start Game");
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int players_num = getNumberofPlayers();
                String [] names = getNames(players_num);
                while(players_num<1 || names==null) {
                    players_num = getNumberofPlayers();
                    names = getNames(players_num);
                }
                if(players_num == 1 || players_num == 2)
                {

                    if(players_num==1){

                        JOptionPane.showMessageDialog(null,"Player 1 keys: a s d f");
                    }
                    if(players_num==2){
                        JOptionPane.showMessageDialog(null,"Player 1 keys: a s d f "+"\n"+"Player 2 keys: h j k l");
                    }
                    frame.dispose();


                    gamePanel = new Game(players_num,names);
                }


            }
        });
        //start_button.setVisible(true);




        frame.add(start);
        frame.add(start1);
        frame.add(start_button);
        frame.setVisible(true);
    }

    /**
     * this method creates a JDialog that takes from players name their name inputs.
     * @param players_num number of players
     * @return names of players
     */
    private String[] getNames(int players_num) {
        JPanel panel = new JPanel(new GridLayout(0,1));
        JTextField [] namesfield = new JTextField[players_num];
        String [] messages = new String[players_num];
        for(int i=0;i<players_num;i++) {
            messages[i] = "Name "+(i+1)+": ";
            namesfield[i] = new JTextField(12);
            panel.add(new JLabel(messages[i]));
            panel.add(namesfield[i]);
            panel.add(Box.createHorizontalStrut(40));
        }
        int result;
        do{
            result = JOptionPane.showConfirmDialog(null,panel,"names message",
                    JOptionPane.DEFAULT_OPTION);
        }while(result != JOptionPane.CLOSED_OPTION && !validnames(players_num, namesfield));

        if(result == JOptionPane.CLOSED_OPTION) {
            return null;
        }
        else
        {
            String [] names = new String[players_num];
            for(int i=0;i<players_num;i++) {
                names[i] = namesfield[i].getText();
            }
            return names;
        }
    }


    /**
     * This method checks either if players have the same name or if a player has given a null name
     * @param players_num The number of players
     * @param namesfield Names of players
     */
    private boolean validnames(int players_num, JTextField[] namesfield) {

        for(int i=0;i<players_num;i++){
            String name = namesfield[i].getText();
            if(name.trim().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"empty name",
                        "empty name title",JOptionPane.ERROR_MESSAGE);
                return false ;
            }
        }
        if(players_num == 2 && namesfield[0].getText().equals(namesfield[1].getText())) {
            JOptionPane.showMessageDialog(null, "same name",
                    "same name title", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method creates a window that gives you the choice for the number of players
     */

    private int getNumberofPlayers() {
        String [] options = {"1","2"};
        int value = JOptionPane.showOptionDialog(null,"Players number",
                "Title",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,
                options,options[0]);
        if(value == JOptionPane.CLOSED_OPTION) {
            return 0;
        }
        else
            return ++value;
    }
}



