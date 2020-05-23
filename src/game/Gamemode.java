package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gamemode {
    private JLabel [][] slots;
    private JButton [] buttons;
    private JPanel grid;
    final int ROWSIZE = 7;
    final int COLSIZE = 6;
    JFrame frame;
    JPanel boardContainer = new JPanel(new BorderLayout());
    JPanel status = new JPanel();
    public Gamemode(JFrame frame) {
        this.frame = frame;
        frame.setLayout(new GridLayout(2,0));
        frame.getContentPane().removeAll();
        setUpBoard();
        frame.revalidate();
        frame.repaint();
    }
    public void setUpBackButton(){
        JButton back = new JButton();
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    MainMenu mm = new MainMenu(frame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setUpBoard(){
        grid = new JPanel(new GridLayout(ROWSIZE, COLSIZE+1)){
            @Override
            public final Dimension getSize(){
                Dimension d = super.getPreferredSize();
                Dimension realSize;
                Component c = getParent();
                if (c==null){
                    realSize = new Dimension ((int)d.getWidth(), (int)d.getHeight());
                }
                else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()){
                    realSize = c.getSize();
                }
                else{
                    realSize = d;
                }
                int smaller = Math.min((int) realSize.getHeight(), (int) realSize.getWidth());
                return new Dimension(smaller,smaller);
            }
        };

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
                grid.add(slots[r][c]);
            }
        }
        boardContainer.add(grid);
    }
}
