package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame{
    //creation of panels to organize the main menu for the frame
    JPanel title = new JPanel();
    JPanel selection = new JPanel();
    //title message
    JLabel titleLabel = new JLabel("Welcome to Connect 4");
    //buttons to enter each specific mode
    JButton twoPlayer = new JButton("Two Player");
    JButton easyAI = new JButton("Singleplayer - Easy");
    JButton hardAI = new JButton("Singleplayer - Hard");

    public Main (){
        //adds title label to the titlebar
        title.add(titleLabel);
        //adds mode selection buttons to the specified jpanel
        selection.add(twoPlayer);
        selection.add(easyAI);
        selection.add(hardAI);
        //adds creates layout to have the two panels stacked on top of each other
        setLayout(new GridLayout(2,1));
        //adds panels to the frame
        add(title);
        add(selection);
        //sets size of the application
        setSize(600,600);
        setVisible(true);
    }
    public static void main(String[] args) {
        //creates instance of the application
        Main m = new Main();
    }
}
