import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int bWidth = 600;
        int bHeight = bWidth;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(bWidth, bHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame s = new SnakeGame(bWidth, bHeight);
        frame.add(s);
        frame.pack();
    }
}
