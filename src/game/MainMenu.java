package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    //static jframe that will be passed around to all the gui classes
    static JFrame frame = new JFrame();
    //creation of panels to organize the main menu for the frame
    JPanel title = new JPanel();
    JPanel selection = new JPanel();
    public void setUpMenu () throws IOException {
        frame.getContentPane().removeAll();
        JMenuBar menuBar = new JMenuBar();
        JMenu settings = new JMenu("Settings");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem instructions = new JMenuItem("Instructions");
        JMenuItem options = new JMenuItem("Options");
        //makes the exit button exit when it is clicked
        exit.addActionListener(
                actionEvent -> System.exit(0)
        );
        //tries to open notepad to open the instructions file if the user selects this menu option
        instructions.addActionListener(
                actionEvent -> {
                    //try-catch here used because we can't throw exceptions using a lambda function
                    try {
                        Desktop.getDesktop().open(new File("./src/Assets/Instructions.txt"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        options.addActionListener(
                actionEvent -> {
                    Options s = new Options();
                }
        );
        //adds various components to the menu bar
        settings.add(instructions);
        settings.add(options);
        settings.add(exit);
        menuBar.add(settings);
        frame.setJMenuBar(menuBar);
        frame.revalidate();
        frame.repaint();
    }
    public void setUpPanel() throws IOException{
        //title message
        JLabel titleLabel = new JLabel("Welcome to Connect 4");
        //buttons to enter each specific mode
        JButton twoPlayer = new JButton("Two Player");
        //if the two player mode is pressed, we are taken to the two player game mode
        twoPlayer.addActionListener(
                actionEvent -> {
                    try {
                        TwoPlayer tp = new TwoPlayer(frame);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        JButton easyAI = new JButton("Singleplayer - Easy");
        easyAI.addActionListener(actionEvent -> {
            try{
                RandomAI rai = new RandomAI(frame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        JButton hardAI = new JButton("Singleplayer - Hard");
        //adds title label to the titlebar
        title.add(titleLabel);
        //adds mode selection buttons to the specified jpanel
        selection.add(twoPlayer);
        selection.add(easyAI);
        selection.add(hardAI);
    }
    public MainMenu(JFrame frame) throws IOException, FontFormatException {
        frame.getContentPane().setBackground(Color.white);
        setUpVisuals();
        setUpMenu();
        setUpPanel();
        //adds creates layout to have the two panels stacked on top of each other
        frame.setLayout(new GridLayout(2,1));
        //adds panels to the frame
        frame.add(title);
        frame.add(selection);
        //sets size of the application
        frame.setSize(600,600);
        frame.setVisible(true);
    }

    private void setUpVisuals() throws IOException, FontFormatException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font montserrat = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Assets/Montserrat-BoldItalic.ttf")).deriveFont(40f);
        ge.registerFont(montserrat);
        UIManager.put("Label.font", montserrat);
        UIManager.put("Button.font", "Arial");
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        //creates instance of the application
        MainMenu m = new MainMenu(frame);
    }
}
