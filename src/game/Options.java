package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options {
    public static int overallDifficulty;
    static String player1CurrentColor = "BLACK.png";
    static String player2CurrentColor = "RED.png";
    static int currentDifficulty = 2;
    static int currentModerateDifficulty = 4;
    public static int timerLength = 15;
    JFrame options = new JFrame();
    public Options(){
        //label selectors
        JLabel colorLabel = new JLabel("Please select the color for player 1:");
        JLabel color2Label = new JLabel("Please select the color for player 2/AI");
        JLabel difficultyLabel = new JLabel("Pl" + "ease select the difficulty for the easy mode:");
        JLabel difficultyLabelMOderate = new JLabel("Pl" + "ease select the difficulty for the moderate mode:");

        //options available in the combo box for colors and difficulty (depth of minmax ai)
        String[] colors = {"BLACK", "ORANGE", "GREEN", "YELLOW", "RED"};
        Integer [] difficulty = {1,2,3,4,5};
        //creation of new comboboxes for the user to select
        JComboBox<String> player1Color = new JComboBox<String>(colors);
        for (int i = 0; i < colors.length; i++){
            if ((colors[i]+".png").equals(player1CurrentColor)){
                player1Color.setSelectedIndex(i);
            }
        }
        JComboBox<String> player2Color = new JComboBox<String>(colors);
        for (int i = 0; i < colors.length; i++){
            if ((colors[i]+".png").equals(player2CurrentColor)){
                player2Color.setSelectedIndex(i);
            }
        }
        JComboBox<Integer> aiDifficulty = new JComboBox<Integer>(difficulty);
        for (int i = 0; i < difficulty.length; i++){
            if (difficulty[i] == currentDifficulty){
                aiDifficulty.setSelectedIndex(i);
            }
        }
        JComboBox<Integer> ai2Difficulty = new JComboBox<Integer>(difficulty);
        for (int i = 0; i < difficulty.length; i++){
            if (difficulty[i] == currentModerateDifficulty){
                ai2Difficulty.setSelectedIndex(i);
            }
        }

        JTextField timerEntry = new JTextField(String.valueOf(timerLength), 5);

        //following 3 blocks of code are adding the label and combobox for a respective to topic to a panel
        JPanel color1Panel = new JPanel();
        color1Panel.add(colorLabel);
        color1Panel.add(player1Color);

        JPanel color2Panel = new JPanel();
        color2Panel.add(color2Label);
        color2Panel.add(player2Color);

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(aiDifficulty);

        JPanel difficulty2 = new JPanel();
        difficulty2.add(difficultyLabelMOderate);
        difficulty2.add(ai2Difficulty);

        JPanel timerLengthPanel = new JPanel();
        timerLengthPanel.add(new JLabel("Please select the length of turn duration"));
        timerLengthPanel.add(timerEntry);

        //creation of exit button which will also put into effect the changes made using the combobox
        JButton saveAndExit = new JButton("Save and Exit");
        //action listener using lambada function
        saveAndExit.addActionListener(
                actionEvent -> {
                    if (player1Color.getSelectedItem().equals(player2Color.getSelectedItem())){
                        JOptionPane.showMessageDialog(new JFrame(), "Player 1 and 2 cannot have same color");
                        return;
                    }
                    try {
                        timerLength = Integer.parseInt(timerEntry.getText());
                    } catch (Exception e){
                        JOptionPane.showMessageDialog(new JFrame(), "Invalid amount of time");
                        return;
                    }
                    //saves the current selected values into the static variables declared at the top of this page
                    player1CurrentColor = String.valueOf(player1Color.getSelectedItem()) + ".png";
                    player2CurrentColor = String.valueOf(player2Color.getSelectedItem()) + ".png";
                    currentDifficulty = Integer.parseInt(String.valueOf(aiDifficulty.getSelectedItem())) + 1;
                    currentModerateDifficulty = Integer.parseInt(String.valueOf(ai2Difficulty.getSelectedItem())) + 1;
                    //makes it invisible so nothing can happen
                    options.setVisible(false);
                }
        );
        //sets layout and adds all of the different panels needed
        options.setLayout(new GridLayout(6,1));
        options.add(color1Panel);
        options.add(color2Panel);
        options.add(difficultyPanel);
        options.add(difficulty2);
        options.add(timerLengthPanel);
        options.add(saveAndExit);
        options.setSize(350,550);
        options.setVisible(true);
    }
}
