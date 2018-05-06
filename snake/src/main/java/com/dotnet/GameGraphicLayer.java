package com.dotnet;

import com.dotnet.character.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameGraphicLayer extends JPanel {
    private UnitResourceManager unitResourceManager;
    private Timer timer;
    private Graphics g;
    private boolean runFlag = true;

    GameGraphicLayer(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;

        setBackground(Color.black);
        setFocusable(true);
    }

    public void addUserKeyListener(KeyListener keyListener) {
        addKeyListener(keyListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        doDrawing();
        if (!runFlag) {
            unitResourceManager.clear();
            showGameOver();
        }
    }

    private void doDrawing() {
        drawBackground();
        Unit[] unitResource = unitResourceManager.getUnitResources();
        for (Unit resource : unitResource) {
            drawImage(resource);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground() {
        g.drawImage(new ImageIcon("res/background2.jpg").getImage(), 0, 0, 1600, 900, this);
    }

    public void gameOver() {
        stop();
        runFlag = false;
        repaint();
    }

    private void showGameOver() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 2000, 2000);

        g.setColor(Color.WHITE);
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 24);

        FontMetrics metr = getFontMetrics(small);
        g.setFont(small);
        g.drawString(msg, (this.getWidth() - metr.stringWidth(msg)) / 2, this.getHeight() / 2);
        repaint();

    }

    private void drawImage(Unit unitResource)  {
        Position p = unitResource.getDrawPosition();
        BufferedImage image= unitResource.getBufferedImage();
        g.drawImage(image, p.getX(), p.getY(), image.getWidth(), image.getHeight(), this);
    }

    public void run() {
        timer = new Timer(16, e -> repaint());
        timer.start();
    }

    private void stop() {
        timer.stop();
    }
}
