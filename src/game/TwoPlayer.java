package game;
/*
 * ICS4U Assignment
 * Names: Andrew Chen, Victor Gao
 * Teacher: Mr. Anandarajan
 * Date: June 10th, 2020
 * Description: Class which is invoked whenever a 2 player game needs to be made
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class TwoPlayer extends Gamemode {
    JFrame frame;
    public TwoPlayer(JFrame frame) throws IOException {
        super(frame);
    }
    public TwoPlayer(JFrame frame, File file) throws IOException, FontFormatException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, URISyntaxException {
        super(frame, file);
    }

}
