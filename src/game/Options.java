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
        JLabel colorLabel = new JLabel("Please select the color for player 1:");
        JLabel color2Label = new JLabel("Please select the color for player 2/AI");
        JLabel difficultyLabel = new JLabel("Pl" + "ease select the difficulty:");
        String [] colors = {"BLACK", "ORANGE", "PURPLE", "YELLOW"};
        Integer [] difficulty = {1,2,3,4,5};
        JComboBox<String> player1Color = new JComboBox<String>(colors);
        JComboBox<String> player2Color = new JComboBox<String>(colors);
        JComboBox<Integer> aiDifficulty = new JComboBox<Integer>(difficulty);

        JPanel color1Panel = new JPanel();
        color1Panel.add(colorLabel);
        color1Panel.add(player1Color);

        JPanel color2Panel = new JPanel();
        color2Panel.add(color2Label);
        color2Panel.add(player2Color);

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(aiDifficulty);

        JButton saveAndExit = new JButton("Save and Exit");
        saveAndExit.addActionListener(
                actionEvent -> {
                    player1CurrentColor = String.valueOf(player1Color.getSelectedItem()) + ".png";
                    player2CurrentColor = String.valueOf(player2Color.getSelectedItem()) + ".png";
                    currentDifficulty = Integer.parseInt(String.valueOf(aiDifficulty.getSelectedItem()));
                    options.setVisible(false);
                }
        );
        options.setLayout(new GridLayout(4,1));
        options.add(color1Panel);
        options.add(color2Panel);
        options.add(difficultyPanel);
        options.add(saveAndExit);
        options.setSize(350,350);
        options.setVisible(true);
    }
}
