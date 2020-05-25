package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Gamemode {
    //array of directions used for the checkWin method
    protected int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    //array of JLabels where the connect 4 tiles are placed
    protected JLabel[][] slots;
    //array correstpinding to slots with an unfilled value being -1, player 1 filled being 1, and player 2 filled being 0
    protected int [][] filled;
    //buttons to place tiles at the top
    protected JButton[] buttons;
    //the panel that hosts the entire grid
    protected JPanel grid;
    //odd turns are player 1, even are player 2
    protected int turn = 1;
    final int ROWSIZE = 7;
    final int COLSIZE = 6;
    JFrame frame;
    /**
     * @param frame - needed to display the gameboard
     * @throws IOException - deals with fileIO
     */
    public Gamemode(JFrame frame) throws IOException {
        this.frame = frame;
        frame.setLayout(new BorderLayout());
        frame.getContentPane().removeAll();
        frame.setTitle("Player 1 Turn");
        setUpBoard();
        setUpMenuBar();
        frame.revalidate();
        frame.repaint();
    }
    /**
     * @param frame - to display the gameboard
     * @param save - save file to load
     */
    public Gamemode(JFrame frame, File save) throws IOException, FontFormatException {
        this.frame = frame;
        frame.setLayout(new BorderLayout());
        frame.getContentPane().removeAll();
        frame.setTitle("Player 1 Turn");
        setUpLoadedBoard(save);
        setUpMenuBar();
        frame.revalidate();
        frame.repaint();
    }

    private void setUpLoadedBoard(File save) throws IOException, FontFormatException {
        //initializing objects described where instance variables are introduced
        slots = new JLabel[ROWSIZE][COLSIZE];
        filled = new int[ROWSIZE][COLSIZE];
        buttons = new JButton[ROWSIZE];
        //creates grid JPanel with appropriate sectioning for the JLabels
        grid = new JPanel(new GridLayout(ROWSIZE, COLSIZE + 1)) {
            //usage of dimension object to size the JLabels
            @Override
            /**
             * Effectively takes the largest possible square possible in the JLabel to place tiles in
             */
            public final Dimension getSize() {
                //gets defualt size of the grid
                Dimension d = super.getPreferredSize();
                Dimension realSize;
                //gets size of the JFrame that contains the panel
                Component c = getParent();
                //if there is no frame (this condition just included in case of later scope changes)
                if (c == null) {
                    realSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
                }
                //if the JFrame is bigger than the default container, set the JFrame container to the real container
                else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    realSize = c.getSize();
                }
                //set size to the JFrame if the JFrame is the smaller one
                else {
                    realSize = d;
                }
                //take the smaller of the lengths and width to set the square length
                int smaller = Math.min((int) realSize.getHeight(), (int) realSize.getWidth());
                return new Dimension(smaller, smaller);
            }
        };
        Scanner fileReader = new Scanner(save);
        int turnNumber = Integer.parseInt(fileReader.nextLine());
        if (turnNumber%2 == 0){
            frame.setTitle("Player 2 Turn");
        }
        setUpButtons();
        //the save file will have a default of -1 as the turn number if nothing was saved into it
        if(turnNumber == -1){
            JOptionPane.showMessageDialog(new JFrame(), "No save file exists, a new game will be created instead",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
            MainMenu mm = new MainMenu(frame);
        }
        else{
            //for every single column represented in the save file
            for (int i = 0; i < COLSIZE; i++){
                //read and split values into a string array
                String [] curCol = fileReader.nextLine().split(" ");
                //for each value in a specificed column
                for (int j = 0; j < ROWSIZE; j++){
                    //fill it with the appropriate info
                    slots [j][i] = new JLabel();
                    filled [j][i] = Integer.parseInt(curCol[j]);
                    //if it is player 1, fill with player 1 colors
                    if (filled[j][i] == 1){
                        slots [j][i].setIcon(new ImageIcon("./src/Assets/" + Options.player1CurrentColor));
                        slots[j][i].setHorizontalAlignment(JLabel.CENTER);
                        slots[j][i].setVerticalAlignment(JLabel.CENTER);
                        turn++;
                    }
                    //if it is player 2, fill with player 2 colors
                    else if (filled[j][i] == 0){
                        slots [j][i].setIcon(new ImageIcon("./src/Assets/" + Options.player2CurrentColor));
                        slots[j][i].setHorizontalAlignment(JLabel.CENTER);
                        slots[j][i].setVerticalAlignment(JLabel.CENTER);
                        turn++;
                    }
                    //add appropriate border
                    slots[j][i].setBorder(new LineBorder(Color.DARK_GRAY));
                    grid.add(slots[j][i]);
                }
            }
        }

        frame.add(grid);
    }

    public void setUpButtons () {
        //adds all of the buttons to the grid
        for (int i = 0; i < ROWSIZE; i++) {
            //numbers the buttons so it is more obvious where to press
            buttons[i] = new JButton(String.valueOf(i + 1));
            //since i use an anonymous functional lambada, i need to duplicate the local variable to use within it
            int finalI = i;
            buttons[i].addActionListener(
                    actionEvent -> {
                        //displays status message on who's turn it is
                        //if the turn is odd, it is player 2's turn
                        if (turn % 2 == 1) {
                            frame.setTitle("Player 2 Turn");
                        }
                        //otherwise it is player 1 turn
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
                                }
                                //if player is player 2
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
                    }
            );
            //add the button to the grid
            grid.add(buttons[i]);
        }
    }


    /**
     * @param x - which row
     * @param y - which column
     * @param value - is this player 1 or player 2, if it is player 1 this value will be 1 while it will be 0 for player 2
     * @throws IOException - involves a bit of FileIO
     */
    public void checkWin(int x, int y, int value) throws IOException, FontFormatException {
        //if all of the tiles have been filled
        if (turn == 42){
            //return to the main menu because it is a tie
            JOptionPane.showMessageDialog(new JFrame(), "A tie has occurred, the game will now reset. The game will now return to the main menu",
                    "information", JOptionPane.INFORMATION_MESSAGE);
            //returns to the main menu
            MainMenu mm = new MainMenu(frame);
        }
        //for all the different directions
        for (int [] direction : directions){
            //check if the current tile placed will cause a connect 4 to happen
            if (checkWinUtility(x,y,value,1,direction)){
                //if this is the player 2 that placed the piece
                if (turn%2==0){
                    //show the dialog box and return to main menu
                    JOptionPane.showMessageDialog(new JFrame(), "Player 2 has won. The game will now return to the main menu",
                            "information", JOptionPane.INFORMATION_MESSAGE);
                    if (this.getClass() == TwoPlayer.class){
                        Statistics.player2Win++;
                    }
                    else{
                        Statistics.computerWin++;
                    }
                }
                //if this is the player 1 that placed the piece
                else{
                    //show the dialog box and return to main menu
                    JOptionPane.showMessageDialog(frame, "Player 1 has won. The game will now return to the main menu",
                            "information", JOptionPane.INFORMATION_MESSAGE);
                    if (this.getClass() == TwoPlayer.class){
                        Statistics.player1Win++;
                    }
                    else{
                        Statistics.playerWin++;
                    }
                }
                //returns to the main menu
                MainMenu mm = new MainMenu(frame);
            }
        }
    }
    /**
     * @param x - which row
     * @param y - which volumn
     * @param value - is this player 1 or player 2, if it is player 1 this value will be 1 while it will be 0 for player 2
     * @param amountInARow - since this is recursive, how many pieces are in a row already
     * @param direction - up down left right or any of the four diagonals, this is represented as an x-y coordinate (eg. up would be (0,1))
     * @return - true if there exists 4 in a row
     */
    public boolean checkWinUtility(int x, int y, int value, int amountInARow, int [] direction){
        //if the amount is 4 in a row, then connect 4 is complete
        if (amountInARow == 4){
            return true;
        }
        //try catch in case the recursive function goes out of bounds
        try {
            //if the next tile in the given direction is the same as the starting one
            if (filled[x + direction[0]][y+direction[1]] == value){
                //keep going until there is 4 in a row
                return checkWinUtility(x + direction[0], y+direction[1], value, amountInARow+1, direction);
            }
            //a different tile from the start means no connect 4 has been achieved
            else{
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            //going out of the array also means that connect 4 has not been reached
            return false;
        }
    }
    /**
     * Sets up the menu bar for the user
     */
    public void setUpMenuBar() {
        //creates new menu bar
        JMenuBar gameMenu = new JMenuBar();
        //creates exit section of menu
        JMenu exit = new JMenu("Exit");
        //creates game section of menu
        JMenu game = new JMenu("Game");
        //creates item to allow user to go back to main menu
        JMenuItem toMainMenu = new JMenuItem("Back to Main Menu");
        //add interaction which creates a new main menuobject, effectively sending the user back to the main menu
        //note usage of lambada function instead of creation of a new action listener to create a more efficent way of functional programming
        toMainMenu.addActionListener(actionEvent -> {
            try {
                //returns to main menu
                MainMenu mm = new MainMenu(frame);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        });
        //creates the item to exit the program entirely
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
            //if no was selected, just close the pane
            //if the optionChosen is null, that is taken as the user not wanting to close
            if (optionChosen == null || optionChosen.equals("Cancel")){
                dialogFrame.setVisible(false);
            }
            //if yes was selected, exit
            else if (optionChosen.equals("Yes")) {
                try {
                    Statistics.saveStats();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(actionEvent -> {
            Object[] possiblities = {"Yes", "No"};
            JFrame dialogFrame = new JFrame();
            //creates a new dialog box with the options yes and no, returns the string that was chosen
            String optionChosen = (String) JOptionPane.showInputDialog(
                    dialogFrame,
                    "This will erase all current save states, do you want to continue?",
                    "Confirmation",
                    JOptionPane.PLAIN_MESSAGE,
                    UIManager.getIcon("FileView.fileIcon"),
                    possiblities,
                    "No"
            );
            //if yes was selected, go into the save method
            if (optionChosen.equals("Yes")) {
                try {
                    saveGame();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //if no was selected, just close the pane
            else {
                dialogFrame.setVisible(false);
            }
        });
        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener((ActionEvent actionEvent) -> {
            try {
                TwoPlayer gm = new TwoPlayer(frame, new File("./src/Assets/SAVE.txt"));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        });
        exit.add(toMainMenu);
        exit.add(exitApplication);
        game.add(saveGame);
        game.add(loadGame);
        gameMenu.add(game);
        gameMenu.add(exit);
        frame.setJMenuBar(gameMenu);
    }

    private void saveGame() throws FileNotFoundException {
        PrintWriter wtf = new PrintWriter(new File("./src/Assets/SAVE.txt"));
        wtf.println(turn);
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                wtf.print(filled[j][i]);
                wtf.print(" ");
            }
            wtf.println();
        }
        wtf.close();
    }

    /** Sets up the board for the user to play
     * @throws IOException - involves FileIO
     */
    public void setUpBoard() throws IOException {
        //creates grid JPanel with appropriate sectioning for the JLabels
        grid = new JPanel(new GridLayout(ROWSIZE, COLSIZE + 1)) {
            //usage of dimension object to size the JLabels
            @Override
            /**
             * Effectively takes the largest possible square possible in the JLabel to place tiles in
             */
            public final Dimension getSize() {
                //gets defualt size of the grid
                Dimension d = super.getPreferredSize();
                Dimension realSize;
                //gets size of the JFrame that contains the panel
                Component c = getParent();
                //if there is no frame (this condition just included in case of later scope changes)
                if (c == null) {
                    realSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
                }
                //if the JFrame is bigger than the default container, set the JFrame container to the real container
                else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    realSize = c.getSize();
                }
                //set size to the JFrame if the JFrame is the smaller one
                else {
                    realSize = d;
                }
                //take the smaller of the lengths and width to set the square length
                int smaller = Math.min((int) realSize.getHeight(), (int) realSize.getWidth());
                return new Dimension(smaller, smaller);
            }
        };
        //initializing objects described where instance variables are introduced
        slots = new JLabel[ROWSIZE][COLSIZE];
        filled = new int[ROWSIZE][COLSIZE];
        buttons = new JButton[ROWSIZE];
        //fills with -1 to signal that nothing has any tiles in it for now
        for (int[] ints : filled) {
            Arrays.fill(ints, -1);
        }
        //adds all of the buttons to the grid
        setUpButtons();
        //add all of the empty slots into the grid
        for (int c = 0; c < COLSIZE; c++) {
            for (int r = 0; r < ROWSIZE; r++) {
                slots[r][c] = new JLabel();
                slots[r][c].setHorizontalAlignment(JLabel.CENTER);
                slots[r][c].setVerticalAlignment(JLabel.CENTER);

                //adding picture to the slot
                slots[r][c].setIcon(new ImageIcon("./src/Assets/board.png"));
                grid.add(slots[r][c]);
            }
        }
        frame.add(grid);
    }
}
