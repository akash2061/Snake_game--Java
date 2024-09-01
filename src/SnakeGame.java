import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int bW;
    int bH;
    int tS = 25;

    Tile snakeHead;
    Tile food;
    Random r;

    Timer gameLoop;

    SnakeGame(int bW, int bH) {
        this.bW = bW;
        this.bH = bH;
        setPreferredSize(new Dimension(bW, bH));
        setBackground(Color.black);

        snakeHead = new Tile(5, 5);
        food = new Tile(10, 10);
        r = new Random();
        placeFood();

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Food
        g.setColor(Color.red);
        g.fillOval(food.x * tS, food.y * tS, tS, tS);

        // Snake
        g.setColor(Color.green);
        // g.fillOval(snakeHead.x * tS, snakeHead.y * tS, tS, tS);
        g.fillRect(snakeHead.x * tS, snakeHead.y * tS, tS, tS);
    }

    public void placeFood() {
        food.x = r.nextInt(bW / tS);
        food.y = r.nextInt(bH / tS);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }
}
