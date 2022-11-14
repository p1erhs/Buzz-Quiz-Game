import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class exists for saving a file with Highscore and most wins depending on players number.
 */
public class HighScore {
    private final int players_num;
    private final Player player1;
    private ArrayList<String> elements;
    String[] temp = {"1","2","3"};
    JButton next = new JButton();
    JFrame frame = new JFrame();
    JLabel label = new JLabel();

    /**
     * Constructor
     * @param players_num number of players.
     * @param player1 temporary field for player.
     */
    public HighScore(int players_num, Player player1) {
        this.players_num = players_num;
        this.player1 = player1;
        elements = new ArrayList<>();


        next.setBounds(510,610,300,40);
        next.setText("Highscores");
        //Button to display file with high scores and most wins if requested.
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //edw mesa prepei na vgazei ta apotelesmata toy arxeiou otan patietai to koympi.
                try{
                    File file = new File("scores.txt");
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        });
        next.setVisible(false);
    }

    /**
     * This method reads a file on given path and saves its lines on an arraylist.
     */
    public void readFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("scores.txt"))) {
            String words;
            while((words=in.readLine())!=null) {
                elements.add(words);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes file with Highscores and most wins.
     */
    public void writeFile()  {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("scores.txt"))) {
            boolean flag = false;
            if(players_num==1){
                elements.add(player1.toScore());
            }
            int max =-50000; //max
            int pos=elements.size()-1; //position after last addition in arraylist
            if(players_num==2) {
                player1.setWins(); // increasing wins.
                elements.add(player1.toWins());
            }
            for(int i=0;i<elements.size()-1;i++){
                temp = elements.get(i).split(":"); // splitting element in position i to 3 parts.temp[2] is the text with wins of player.
                if(temp[2].equals(player1.getName()) && players_num==2 && i!= elements.size()-1){ //if name already exists then increase wins.
                    flag = true;
                    int win = Integer.parseInt(temp[1]); //wins converted string.
                    temp[1] = String.valueOf(++win); //increasing wins.
                    String set = temp[0]+":"+temp[1]+":"+temp[2];
                    elements.set(i,set); //overwrite player with extra win.
                }
                if(temp[0].equals("Score")) {
                    int score = Integer.parseInt(temp[1]);
                    if(score>max){ //finding max score.
                        max=score;
                        pos=i;
                    }
                }
            }
            if(players_num==1){
                if(max>player1.getScore()) {
                    elements.remove(elements.size() - 1); //if player in current game didn't hit High score then removing him from arraylist.
                    elements.set(pos, elements.get(pos));
                }
                if((player1.getScore()>max) && (pos != elements.size() - 1)){
                    elements.remove(pos);
                }

            }
            if(flag)
                elements.remove(elements.size()-1); //if player in current game already existed just removing new element.
            elements.sort(Collections.reverseOrder());

            //writing file.
            for (String element : elements) {
                out.write(element);
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return button next
     */
    public JButton getHigh(){
        return next;
    }


}