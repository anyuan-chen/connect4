package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options {
    static String player1CurrentColor = "BLACK.png";
    static String player2CurrentColor = "ORANGE.png";
    static int currentDifficulty = 5;

    JFrame options = new JFrame();
    public Options(){
        //label selectors
        JLabel colorLabel = new JLabel("Please select the color for player 1:");
        JLabel color2Label = new JLabel("Please select the color for player 2/AI");
        JLabel difficultyLabel = new JLabel("Pl" + "ease select the difficulty:");
        //options available in the combo box for colors and difficulty (depth of minmax ai)
        String [] colors = {"BLACK", "ORANGE", "PURPLE", "YELLOW"};
        Integer [] difficulty = {1,2,3,4,5};
        //creation of new comboboxes for the user to select
        JComboBox<String> player1Color = new JComboBox<String>(colors);
        JComboBox<String> player2Color = new JComboBox<String>(colors);
        JComboBox<Integer> aiDifficulty = new JComboBox<Integer>(difficulty);

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

        //creation of exit button which will also put into effect the changes made using the combobox
        JButton saveAndExit = new JButton("Save and Exit");
        //action listener using lambada function
        saveAndExit.addActionListener(
                actionEvent -> {
                    //saves the current selected values into the static variables declared at the top of this page
                    player1CurrentColor = String.valueOf(player1Color.getSelectedItem()) + ".png";
                    player2CurrentColor = String.valueOf(player2Color.getSelectedItem()) + ".png";
                    currentDifficulty = Integer.parseInt(String.valueOf(aiDifficulty.getSelectedItem()));
                    //makes it invisible so nothing can happen
                    options.setVisible(false);
                }
        );
        //sets layout and adds all of the different panels needed
        options.setLayout(new GridLayout(4,1));
        options.add(color1Panel);
        options.add(color2Panel);
        options.add(difficultyPanel);
        options.add(saveAndExit);
        options.setSize(350,350);
        options.setVisible(true);
    }
}
