package com.dotnet;

import com.dotnet.character.Apple;
import com.dotnet.character.snake.UserSnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class GameControlLayer {
    private UserSnake userSnake;
    private Apple apple;
    private Timer timer;
    private GameGraphicLayer gameGraphicLayer;

    public GameControlLayer() {

    }

/*    public void checkApple() {
        if ((snake.getX()[0] == apple.getX()) && (snake.getY()[0] == apple.getY())) {
            snake.incrementDots();
            locateApple();
        }
    }*/

    private void move() {
        userSnake.move();
    }

    private void locateApple() {
       // int r = (int) (Math.random() * gameDataLayer.getRAND_POS());
       // int r2 = (int) (Math.random() * gameDataLayer.getRAND_POS());
        int r = 250;
        int r2 = 350;
        apple.setX(r);
        apple.setY(r2);
    }

    public void runGame() {
        apple = new Apple();
        userSnake = new UserSnake();

        DrawResourceManager drawResourceManager = new DrawResourceManager();
        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
        ScreenConfig screenConfig = new ScreenConfig();
        GameDataLayer gameDataLayer =  new GameDataLayer(drawResourceManager, screenConfig);
        gameGraphicLayer = new GameGraphicLayer(drawResourceManager);

        gameGraphicLayer.setPreferredSize(new Dimension(screenConfig.getWidth(), screenConfig.getHeight()));

        gameGraphicLayer.run();
        gameGraphicLayer.addUserKeyListener(gameKeyAdapter);
        gameKeyAdapter.setKeyListener(userSnake);

        drawResourceManager.addDrawResource(apple.getDrawResource());
        drawResourceManager.addDrawResource(userSnake.getDrawResource());

        locateApple();

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameDataLayer.isInGame()) {

                   /* gameControlLayer.checkApple();
                    gameControlLayer.checkCollision();*/
                    move();
                }
                if (!gameDataLayer.isInGame()) {
                    timer.stop();
                }
            }
        };

        timer = new Timer(gameDataLayer.getDELAY(), actionListener);
        timer.start();
    }


    public GameGraphicLayer getGameGraphicLayer() {
        return gameGraphicLayer;
    }
}
