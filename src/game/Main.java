package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    //creation of panels to organize the main menu for the frame
    JPanel title = new JPanel();
    JPanel selection = new JPanel();
    public void setUpMenu () throws IOException {
        JMenuBar menuBar = new JMenuBar();
        JMenu settings = new JMenu("Settings");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem instructions = new JMenuItem("Instructions");
        //makes the exit button exit when it is clicked
        exit.addActionListener(
                actionEvent -> System.exit(0)
        );
        //tries to open notepad to open the instructions file if the user selects this menu option
        instructions.addActionListener(
                actionEvent -> {
                    //try-catch here used because we can't throw exceptions using a lambda function
                    try {
                        Desktop.getDesktop().open(new File("./Assets/Instructions.txt"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        //adds various components to the menu bar
        settings.add(instructions);
        settings.add(exit);
        menuBar.add(settings);
        setJMenuBar(menuBar);
    }
    public void setUpPanel(){
        //title message
        JLabel titleLabel = new JLabel("Welcome to Connect 4");
        //buttons to enter each specific mode
        JButton twoPlayer = new JButton("Two Player");
        JButton easyAI = new JButton("Singleplayer - Easy");
        JButton hardAI = new JButton("Singleplayer - Hard");
        //adds title label to the titlebar
        title.add(titleLabel);
        //adds mode selection buttons to the specified jpanel
        selection.add(twoPlayer);
        selection.add(easyAI);
        selection.add(hardAI);
    }
    public Main () throws IOException{
        setUpMenu();
        setUpPanel();
        //adds creates layout to have the two panels stacked on top of each other
        setLayout(new GridLayout(2,1));
        //adds panels to the frame
        add(title);
        add(selection);
        //sets size of the application
        setSize(600,600);
        setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        //creates instance of the application
        Main m = new Main();
    }
}
