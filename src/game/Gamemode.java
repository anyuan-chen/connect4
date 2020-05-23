package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class Gamemode {
    private JLabel[][] slots;
    private int [][] filled;
    private JButton[] buttons;
    private JPanel grid;
    int turn = 1;
    final int ROWSIZE = 7;
    final int COLSIZE = 6;
    JFrame frame;

    public Gamemode(JFrame frame) {
        this.frame = frame;
        frame.setLayout(new BorderLayout());
        frame.getContentPane().removeAll();
        setUpBoard();
        setUpMenuBar();
        frame.revalidate();
        frame.repaint();
    }
    public void setUpMenuBar() {
        JMenuBar gameMenu = new JMenuBar();
        JMenu exit = new JMenu("Exit");
        JMenu game = new JMenu("Game");
        JMenuItem toMainMenu = new JMenuItem("Back to Main Menu");
        toMainMenu.addActionListener(actionEvent -> {
            try {
                MainMenu mm = new MainMenu(frame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        JMenuItem exitApplication = new JMenuItem("Exit Application");
        exitApplication.addActionListener(actionEvent -> {
            //creates two options for the user to choose to confirm or not
            Object[] possiblities = {"Yes", "No"};
            //creates frame for the dialog box to appear
            JFrame dialogFrame = new JFrame();
            //creates a new dialog box with the options yes and no, returns the string that was chosen
            String optionChosen = (String) JOptionPane.showInputDialog(
                    dialogFrame,
                    "Do you really want to exit?",
                    "Confirmation",
                    JOptionPane.PLAIN_MESSAGE,
                    UIManager.getIcon("FileView.fileIcon"),
                    possiblities,
                    "Yes"
            );
            //if yes was selected, exit
            if (optionChosen.equals("Yes")) {
                System.exit(0);
            }
            //if no was selected, just close the pane
            else {
                dialogFrame.setVisible(false);
            }
        });
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem loadGame = new JMenuItem("Load Game");
        exit.add(toMainMenu);
        exit.add(exitApplication);
        game.add(saveGame);
        game.add(loadGame);
        gameMenu.add(game);
        gameMenu.add(exit);
        frame.setJMenuBar(gameMenu);
    }
    public void setUpBoard() {
        grid = new JPanel(new GridLayout(ROWSIZE, COLSIZE + 1)) {
            @Override
            public final Dimension getSize() {
                Dimension d = super.getPreferredSize();
                Dimension realSize;
                Component c = getParent();
                if (c == null) {
                    realSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
                } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    realSize = c.getSize();
                } else {
                    realSize = d;
                }
                int smaller = Math.min((int) realSize.getHeight(), (int) realSize.getWidth());
                return new Dimension(smaller, smaller);
            }
        };
        slots = new JLabel[ROWSIZE][COLSIZE];
        filled = new int[ROWSIZE][COLSIZE];
        buttons = new JButton[ROWSIZE];
        for (int[] ints : filled) {
            Arrays.fill(ints, -1);
        }
        for (int i = 0; i < ROWSIZE; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
            int finalI = i;
            buttons[i].addActionListener(
                    actionEvent -> {
                       for (int c = COLSIZE-1; c >= 0; c--){
                           if (filled[finalI][c] == -1){
                               filled[finalI][c] = turn%2;
                               if (turn%2==1){
                                   slots [finalI][c].setIcon(new ImageIcon("./src/Assets/" + Options.player1CurrentColor));
                               }
                               else{
                                   slots [finalI][c].setIcon(new ImageIcon("./src/Assets/" + Options.player2CurrentColor));
                               }
                               turn++;
                               frame.repaint();
                               frame.revalidate();
                               break;
                           }
                           if (c==0){
                               JOptionPane.showMessageDialog(frame, "This column is full, try another");
                           }
                       }
                    }
            );
            grid.add(buttons[i]);
        }
        for (int c = 0; c < COLSIZE; c++) {
            for (int r = 0; r < ROWSIZE; r++) {
                slots[r][c] = new JLabel();
                slots[r][c].setBorder(new LineBorder(Color.DARK_GRAY));
                grid.add(slots[r][c]);
            }
        }
        frame.add(grid);
    }
}
