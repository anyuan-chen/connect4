package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Gamemode {
    private JLabel [][] slots;
    private JButton [] buttons;
    private JPanel grid;
    final int ROWSIZE = 7;
    final int COLSIZE = 6;
    JFrame frame;

    public Gamemode(JFrame frame) {
        this.frame = frame;
        frame.getContentPane().removeAll();
        setUpBoard();
        frame.revalidate();
        frame.repaint();
    }
    public void setUpBoard(){
        grid = new JPanel();
        grid.setLayout(new GridLayout(ROWSIZE, COLSIZE+1));
        slots = new JLabel[ROWSIZE][COLSIZE];
        buttons = new JButton[ROWSIZE];
        for (int i = 0; i < ROWSIZE; i++){
            buttons [i] = new JButton(String.valueOf(i+1));
            buttons[i].setPreferredSize(new Dimension(60,60));
            grid.add(buttons[i]);
        }
        for (int c = 0; c < COLSIZE; c++){
            for (int r = 0; r < ROWSIZE; r++){
                slots[r][c] = new JLabel();
                slots [r][c].setBorder(new LineBorder(Color.DARK_GRAY));
                slots [r][c].setPreferredSize(new Dimension(60,60));
                slots [r][c].setHorizontalAlignment(SwingConstants.CENTER);
                grid.add(slots[r][c]);
            }
        }
        frame.add(grid);
    }
}
