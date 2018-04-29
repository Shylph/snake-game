package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.UserSnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControlLayer {
    private UserSnake userSnake;
    private Unit apple;
    private Unit ppi;
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

    private void initStartPosition() {
        // int r = (int) (Math.random() * gameDataLayer.getRAND_POS());
        // int r2 = (int) (Math.random() * gameDataLayer.getRAND_POS());
        int r = 250;
        int r2 = 350;
        apple.setPosition(new Position(r,r2));
        ppi.setPosition(new Position(350,450));
        userSnake.setPosition(new Position(200,200));
    }

    public void runGame() {
        DrawResourceManager drawResourceManager = new DrawResourceManager();
        UnitMaker unitMaker = new UnitMaker(drawResourceManager);
        ppi = unitMaker.makePpi();
        apple = unitMaker.makeRabbit();
        userSnake = unitMaker.makeUserSnake();

        initStartPosition();
        userSnake.incrementBody(drawResourceManager);
        userSnake.incrementBody(drawResourceManager);

        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
        ScreenConfig screenConfig = new ScreenConfig();
        GameDataLayer gameDataLayer = new GameDataLayer(drawResourceManager, screenConfig);
        gameGraphicLayer = new GameGraphicLayer(drawResourceManager);

        gameGraphicLayer.setPreferredSize(new Dimension(screenConfig.getWidth(), screenConfig.getHeight()));

        gameGraphicLayer.run();
        gameGraphicLayer.addUserKeyListener(gameKeyAdapter);
        gameKeyAdapter.setKeyListener(userSnake);



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
