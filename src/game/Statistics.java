package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistics {
    static int playerWin = 0;
    static int computerWin = 0;
    static int player1Win = 0;
    static int player2Win = 0;

    JFrame jf = new JFrame();
    public Statistics() {
        jf.setLayout(new GridLayout(3,1));
        addPVPStats();
        addPVCStats();
        JButton exit = new JButton("Exit");
        exit.addActionListener(actionEvent -> jf.setVisible(false));
        exit.setPreferredSize(new Dimension(30,10));
        jf.add(exit);
        jf.setSize(400,300);
        jf.setVisible(true);
    }
    private void addPVCStats() {
        JPanel playerVSComputer = new JPanel();
        JLabel playerWinLabel = new JLabel("The amount of times the player has beat the CPU is " + playerWin);
        JLabel computerWinLabel = new JLabel("The amount of times the player has lost is " + computerWin);
        playerVSComputer.add(playerWinLabel);
        playerVSComputer.add(computerWinLabel);
        jf.add(playerVSComputer);
    }
    private void addPVPStats() {
        JPanel playerVSPlayer = new JPanel();
        JLabel playerWinLabel = new JLabel("The amount of times Player 1 has won is " + player1Win);
        JLabel computerWinLabel = new JLabel("The amount of times Player 2 has won is " + player2Win);
        playerVSPlayer.add(playerWinLabel);
        playerVSPlayer.add(computerWinLabel);
        jf.add(playerVSPlayer);
    }
}
