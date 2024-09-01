import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

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
    ArrayList<Tile> snakeBody;

    Tile food;
    Random r;

    // game logic
    Timer gameLoop;
    int vX;
    int vY;
    boolean gameOver = false;

    SnakeGame(int bW, int bH) {
        this.bW = bW;
        this.bH = bH;
        setPreferredSize(new Dimension(this.bW, this.bH));
        setBackground(Color.black);
        addKeyListener((this));
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        r = new Random();
        placeFood();

        vX = 0;
        vY = 0;

        gameLoop = new Timer(150, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        // Food
        g.setColor(Color.red);
        // g.fillRect(food.x * tS, food.y * tS, tS, tS);
        g.fillOval(food.x * tS, food.y * tS, tS, tS);

        // snakehead
        g.setColor(Color.green);
        // g.fillRect(snakeHead.x * tS, snakeHead.y * tS, tS,tS);
        g.fill3DRect(snakeHead.x * tS, snakeHead.y * tS, tS, tS, true);

        // snakebody
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            // g.fillRect(snakePart.x * tS, snakePart.y * tS, tS, tS);
            g.fill3DRect(snakePart.x * tS, snakePart.y * tS, tS, tS, true);
        }

        g.setFont(new Font("Arial", Font.PLAIN, 16));

        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tS - 16, tS);
        } else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tS - 16, tS);
        }

    }

    public void placeFood() {
        food.x = r.nextInt(bW / tS);
        food.y = r.nextInt((bH / tS));
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {

        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        snakeHead.x += vX;
        snakeHead.y += vY;

        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x * tS < 0 || snakeHead.x * tS > bW || snakeHead.y * tS < 0
                || snakeHead.y * tS > bH) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && vY != 1) {
            vX = 0;
            vY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && vY != -1) {
            vX = 0;
            vY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && vX != 1) {
            vX = -1;
            vY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && vX != -1) {
            vX = 1;
            vY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}