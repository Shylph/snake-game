package com.dotnet;


import com.dotnet.character.Unit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Main extends JFrame {

    public Main() {

        GameControlLayer gameControlLayer = new GameControlLayer();
        gameControlLayer.runGame();

        add(gameControlLayer.getGameGraphicLayer());

        setResizable(false);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/bgm_nature.wav"));
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}