package src.entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpielerRechteck extends JPanel implements KeyListener {

    private int player1X = 200, player1Y = 300, player1VelocityY = 0; // Größe der Spieler
    private int player2X = 600, player2Y = 300, player2VelocityY = 0;
    private final int width = 500, height = 500;
    private final int groundY = 300;
    private final int gravity = 1;
    private final int jumpStrength = -15;

    private boolean player1Left, player1Right, player1Jumping, player1Crouching;
    private boolean player2Left, player2Right, player2Jumping, player2Crouching;

    private Image player1Image, player2Image;

    public SpielerRechteck() {
        setFocusable(true);
        addKeyListener(this);

        ImageIcon player1Icon = new ImageIcon("Streetfighters Projekt\\Snapshot 1.2\\bin\\Spieler\\spieler_1_stillstehen.gif"); // Pfad zum gif
        ImageIcon player2Icon = new ImageIcon("Streetfighters Projekt\\Snapshot 1.2\\bin\\Spieler\\spieler_1_stillstehen.gif");

        player1Image = player1Icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        player2Image = player2Icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(player1Image, player1X, player1Y, null);
        g.drawImage(player2Image, player2X, player2Y, null);
    }

    public void updateGame() {
        // Spieler 1 Bewegung
        if (player1Left) {
            player1X -= 10;
            System.out.println("Spieler 1 Position: x=" + player1X + " y=" + player1Y);
        } // Geschwindigkeit
        if (player1Right) {
            player1X += 10;
            System.out.println("Spieler 1 Position: x=" + player1X + " y=" + player1Y);
        }

        // Schwerkraft für Spieler 1
        if (player1Y < groundY) {
            player1VelocityY += gravity;
        } else {
            player1Y = groundY;
            player1VelocityY = 0;
            player1Jumping = false;
        }
        player1Y += player1VelocityY;

        if (player1Crouching) {
            player1Y = groundY + 70;
            System.out.println("Spieler 1 Position: x=" + player1X + " y=" + player1Y);
        } // Ducktiefe

        if (player1Jumping) {
            player1Y = groundY - 70;
            System.out.println("Spieler 1 Position: x=" + player1X + " y=" + player1Y);
        } // Springen

        // Spieler 2 Bewegung
        if (player2Left) {
            player2X -= 10;
            System.out.println("Spieler 2 Position: x=" + player2X + " y=" + player2Y);
        } 
        if (player2Right) {
            player2X += 10;
            System.out.println("Spieler 2 Position: x=" + player2X + " y=" + player2Y);
        }

        // Schwerkraft für Spieler 2
        //if (player2Y < groundY) {
        //    player2VelocityY += gravity;
        //} else {
        //    player2Y = groundY;
         //   player2VelocityY = 0;
         //   player2Jumping = false;
        //}
        //player2Y += player2VelocityY;

        if (player2Crouching) {
            player2Y = groundY + 70;
            System.out.println("Spieler 2 Position: x=" + player2X + " y=" + player2Y);
        } // Ducktiefe

        if (player2Jumping) {
            player2Y = groundY - 70;
            System.out.println("Spieler 2 Position: x=" + player2X + " y=" + player2Y);
        } // Springen

        repaint();
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) player1Left = true;
        if (key == KeyEvent.VK_D) player1Right = true;
        if (key == KeyEvent.VK_W) player1Jumping = true;
        if (key == KeyEvent.VK_S) player1Crouching = true;

        if (key == KeyEvent.VK_LEFT) player2Left = true;
        if (key == KeyEvent.VK_RIGHT) player2Right = true;
        if (key == KeyEvent.VK_UP) player2Jumping = true;
        if (key == KeyEvent.VK_DOWN) player2Crouching = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) player1Left = false;
        if (key == KeyEvent.VK_D) player1Right = false;
        if (key == KeyEvent.VK_S) player1Crouching = false;
        if (key == KeyEvent.VK_W) player1Jumping = false;

        if (key == KeyEvent.VK_LEFT) player2Left = false;
        if (key == KeyEvent.VK_RIGHT) player2Right = false;
        if (key == KeyEvent.VK_DOWN) player2Crouching = false;
        if (key == KeyEvent.VK_UP) player2Jumping = false;
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
