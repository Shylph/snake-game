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
        gameDataLayer = new GameDataLayer(unitResourceManager);

        ScreenConfig screenConfig = new ScreenConfig();
        gameGraphicLayer.setPreferredSize(new Dimension(screenConfig.getWidth(), screenConfig.getHeight()));
    }

    private void gameProcess() {
        if(gameDataLayer.checkFenceCollision(userSnake)){
            stopGame();
            gameGraphicLayer.gameOver();
        }
        gameDataLayer.checkFoodCollision(userSnake);
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
        userSnake.setPosition(new Position(510,450));
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
                     gameProcess();
                }
                if (!gameDataLayer.isInGame()) {
                    stopGame();
                }
            }
        };

        timer = new Timer(gameDataLayer.getDELAY(), actionListener);
        timer.start();
    }

    private void stopGame(){
        timer.stop();
    }

    public GameGraphicLayer getGameGraphicLayer() {
        return gameGraphicLayer;
    }
}
