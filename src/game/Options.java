package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    static String color = "black";
    static int difficulty = 5;
    JFrame settings = new JFrame();
    public Settings(){
        JLabel colorLabel = new JLabel("Please select the color for player 1:");
        JLabel color2Label = new JLabel("Please select the color for player 2/AI");
        JLabel difficultyLabel = new JLabel("Please select the difficulty:");

        String [] colors = {"red", "blue", "yellow"};
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
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        settings.setVisible(false);
                    }
                }
        );
        settings.setLayout(new GridLayout(4,1));
        settings.add(color1Panel);
        settings.add(color2Panel);
        settings.add(difficultyPanel);
        settings.add(saveAndExit);
        settings.setSize(350,350);
        settings.setVisible(true);
    }
}
