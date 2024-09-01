import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel {

    int bW;
    int bH;

    SnakeGame(int bW, int bH) {
        this.bW = bW;
        this.bH = bH;
        setPreferredSize(new Dimension(bW, bH));
        setBackground(Color.black);
    }
}
