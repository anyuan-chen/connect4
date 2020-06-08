package game;
/*
 * ICS4U Assignment
 * Names: Andrew Chen, Victor Gao
 * Teacher: Mr. Anandarajan
 * Date: June 10th, 2020
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Statistics {
    static int playerWin = -1;
    static int computerWin = -1;
    static int player1Win = -1;
    static int player2Win = -1;

    JFrame jf = new JFrame();
    public Statistics() throws FileNotFoundException, URISyntaxException {
        jf.setLayout(new GridLayout(3,1));
        addPVPStats();
        addPVCStats();
        JButton exit = new JButton("Exit");
        exit.addActionListener(actionEvent -> jf.setVisible(false));
        exit.setPreferredSize(new Dimension(30,10));
        URL rscIcon = ClassLoader.getSystemResource("connect4icon.png");
        ImageIcon icon = new ImageIcon(String.valueOf(rscIcon.toURI()));
        jf.setIconImage(icon.getImage());
        jf.add(exit);
        jf.setSize(400,300);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    public static void importStats() throws FileNotFoundException {
        InputStream stream = Statistics.class.getResourceAsStream("/STATISTICS.txt");
        Scanner statsReader = new Scanner(stream);
        playerWin = Integer.parseInt(statsReader.nextLine());
        System.out.println(playerWin);
        computerWin = Integer.parseInt(statsReader.nextLine());
        player1Win = Integer.parseInt(statsReader.nextLine());
        player2Win = Integer.parseInt(statsReader.nextLine());
    }

    public static void saveStats() throws FileNotFoundException, URISyntaxException {
        URL rsct = ClassLoader.getSystemResource("STATISTICS.txt");
        File statsFile = new File(rsct.toURI());
        PrintWriter statsSave = new PrintWriter(statsFile);
        statsSave.println(playerWin);
        statsSave.println(computerWin);
        statsSave.println(player1Win);
        statsSave.println(player2Win);
        statsSave.close();
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
