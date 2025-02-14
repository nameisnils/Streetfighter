package src.main;

import javax.swing.JFrame;
import src.entities.SpielerRechteck;
import java.awt.Toolkit;
import java.awt.Dimension;

public class GameWindow extends JFrame implements Runnable {

    private SpielerRechteck spieler;
    private boolean running = false;
    private final int FPS = 60;

    public GameWindow() {
        System.out.println("Fenster lädt");

        setTitle("Streetfighters");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        // Spieler-Panel hinzufügen
        spieler = new SpielerRechteck();
        spieler.setBounds(0, 0, screenSize.width, screenSize.height);
        add(spieler);

        // KeyListener aktivieren
        spieler.setFocusable(true);
        spieler.requestFocus();

        // GameLoop starten
        startGameLoop();
    }

    private void startGameLoop() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerFrame = 1000000000.0 / FPS;
        double delta = 0;
    
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;
    
            // Update-Spielzustand
            if (delta >= 1) {
                spieler.updateGame();
                delta--;
            }

            try {
                Thread.sleep(1); // Kleinere Pause, damit die CPU nicht überlastet wird
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}