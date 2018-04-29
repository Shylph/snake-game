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
    private UnitResourceManager unitResourceManager;
    private UnitMaker unitMaker;
    private GameDataLayer gameDataLayer;

    public GameControlLayer() {
        unitResourceManager = new UnitResourceManager();
        unitMaker = new UnitMaker(unitResourceManager);

        gameGraphicLayer = new GameGraphicLayer(unitResourceManager);
        ScreenConfig screenConfig = new ScreenConfig();
        gameDataLayer = new GameDataLayer(unitResourceManager, screenConfig);

        gameGraphicLayer.setPreferredSize(new Dimension(screenConfig.getWidth(), screenConfig.getHeight()));

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
        ppi = unitMaker.makePpi();
        apple = unitMaker.makeRabbit();
        userSnake = unitMaker.makeUserSnake();
        apple.setPosition(new Position(250,250));
        ppi.setPosition(new Position(350,450));
        userSnake.setPosition(new Position(200,200));
        userSnake.incrementBody(unitResourceManager);
        userSnake.incrementBody(unitResourceManager);
    }

    public void runGame() {
        initStartPosition();

        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
        gameGraphicLayer.addUserKeyListener(gameKeyAdapter);
        gameKeyAdapter.setKeyListener(userSnake);

        gameGraphicLayer.run();

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
