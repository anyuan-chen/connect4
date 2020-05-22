package game;

import javax.swing.*;

public class TwoPlayer {
    JFrame frame;
    public TwoPlayer(JFrame frame){
        this.frame = frame;
        frame.getContentPane().removeAll();
        setUpMainBoard();
        frame.revalidate();
        frame.repaint();
    }
    public void setUpMainBoard(){
        JPanel mainPanel = new JPanel();
        JLabel lb = new JLabel("placeholder for actual board");
        mainPanel.add(lb);
        frame.add(mainPanel);
    }
}
