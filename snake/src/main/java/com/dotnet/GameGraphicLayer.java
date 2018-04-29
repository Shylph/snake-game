package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.imageSource.ImageSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class GameGraphicLayer extends JPanel {
    private final DrawResourceManager drawResourceManager;
    private Timer timer;

    public GameGraphicLayer(DrawResourceManager drawResourceManager) {
        this.drawResourceManager = drawResourceManager;

        setBackground(Color.black);
        setFocusable(true);
    }

    public void addUserKeyListener(KeyListener keyListener) {
        addKeyListener(keyListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        drawBackground(g);
        Unit[] drawResource = drawResourceManager.getDrawResources();
        for (Unit resource : drawResource) {
            //   if (!resource.isImgNull())
            drawImage(g, resource);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(ImageSource.getBackground(), 0, 0, 1600, 900, this);
    }

    public void showGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (this.getWidth() - metr.stringWidth(msg)) / 2, this.getHeight() / 2);
        repaint();
    }

    private void drawImage(Graphics g, Unit drawResource) {
        Position p = drawResource.getPoint();
        g.drawImage(drawResource.getImg(), p.getX(), p.getY(), drawResource.getWidth(), drawResource.getHeight(), this);
    }

    public void run() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        };

        timer = new Timer(16, actionListener);
        timer.start();
    }
}
