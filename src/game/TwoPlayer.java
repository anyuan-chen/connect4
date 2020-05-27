package game;

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
