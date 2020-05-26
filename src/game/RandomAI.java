/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class RandomAI extends Gamemode {

    MinimaxAI ai = new MinimaxAI(Options.currentDifficulty, 1, 0);

    public RandomAI(JFrame frame) throws IOException {
        super(frame);
    }

    public RandomAI(JFrame frame, File save) throws IOException, FontFormatException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super(frame, save);
    }

    @Override
    public void setUpButtons() {
        //adds all of the buttons to the grid
        for (int i = 0; i < ROWSIZE; i++) {
            //numbers the buttons so it is more obvious where to press
            buttons[i] = new JButton(new ImageIcon("./src/Assets/arrow.png"));
            buttons[i].setBackground(col);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            //since i use an anonymous functional lambada, i need to duplicate the local variable to use within it
            int finalI = i;
            buttons[i].addActionListener(
                    actionEvent -> {
                        //displays status message on who's turn it is
                        //if the turn is odd, it is player 2's turn
                        if (turn % 2 == 1) {
                            frame.setTitle("Player 2 Turn");
                        } //otherwise it is player 1 turn
                        else {
                            frame.setTitle("Player 1 Turn");
                        }
                        //looks for the lowest availiabe position in the column to simulate gravity
                        for (int c = COLSIZE - 1; c >= 0; c--) {
                            //if there is a nonfilled spot
                            if (filled[finalI][c] == -1) {
                                //mark it as filled
                                filled[finalI][c] = turn % 2;
                                //if player is player 1
                                if (turn % 2 == 1) {
                                    slots[finalI][c].setIcon(new ImageIcon("./src/Assets/" + Options.player1CurrentColor));
                                } //if player is player 2
                                else {
                                    slots[finalI][c].setIcon(new ImageIcon("./src/Assets/" + Options.player2CurrentColor));
                                }
                                //centers the icon so that nothing bad can happen to it
                                slots[finalI][c].setHorizontalAlignment(JLabel.CENTER);
                                slots[finalI][c].setVerticalAlignment(JLabel.CENTER);
                                //checks if there is any player that has just won off of this tile placement
                                //the checkwin will automatically terminate this object if there is a winner
                                try {
                                    checkWin(finalI, c, turn % 2);
                                } catch (IOException | FontFormatException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedLookAndFeelException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                //increases the turn
                                turn++;
                                //repains and revalidates the frame
                                frame.repaint();
                                frame.revalidate();
                                //stops looking for another tile
                                break;
                            }
                            //if no tile was empty, force the user to select another column
                            if (c == 0) {
                                JOptionPane.showMessageDialog(frame, "This column is full, try another");
                            }
                        }
                        try {
                            aiMove();
                        } catch (IOException | InterruptedException | FontFormatException e) {
                            e.printStackTrace();
                        }
                    }
            );
            //add the button to the grid
            grid.add(buttons[i]);
        }
    }
    int[][] newArray = new int[filled[0].length][filled.length];

    public void reverseFilled() {
        for (int i = 0; i < filled.length; i++) {
            for (int j = 0; j < filled[0].length; j++) {
                newArray[j][i] = filled[i][j];
            }
        }
    }

    private void aiMove() throws IOException, InterruptedException, FontFormatException {
        reverseFilled();
        if (turn % 2 == 0) {
            boolean validChoice = false;

            /**
             * for (int i = 0; i < newArray.length; i++) {
             * System.out.println(Arrays.toString(newArray[i])); }
             * System.out.println("diff " + Options.currentDifficulty);
             *
             */
            int high = ai.move(Options.currentDifficulty, true, newArray);
            //System.out.println(high);
            int choose = ai.makeMove(high, newArray);

            frame.repaint();
            frame.revalidate();
            buttons[choose].doClick();

        }
    }

}
