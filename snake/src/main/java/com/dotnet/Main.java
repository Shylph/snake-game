package com.dotnet;


import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {

    private Main() {

        GameControlLayer gameControlLayer = new GameControlLayer();
        ModeSelectLayer modeSelectLayer = new ModeSelectLayer();
        add(modeSelectLayer);

        modeSelectLayer.setArcadeModeListener(e -> {
            gameControlLayer.runArcadeGame();

            add(gameControlLayer.getGameGraphicLayer());
            modeSelectLayer.setVisible(false);
        });

        modeSelectLayer.setFightModeListener(e -> {
            gameControlLayer.runFightGame();

            add(gameControlLayer.getGameGraphicLayer());
            modeSelectLayer.setVisible(false);
        });

        setResizable(false);
        setPreferredSize(new Dimension(1600,900));
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