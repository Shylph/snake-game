package com.dotnet;


import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {

    private Main() {

        GameControlLayer gameControlLayer = new GameControlLayer();
        gameControlLayer.runGame();

        add(gameControlLayer.getGameGraphicLayer());

        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });
    }
}