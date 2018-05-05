package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.UserSnake;

import javax.swing.*;
import java.awt.*;

public class GameControlLayer {
    private UserSnake userSnake;
    private Unit rabbit;
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
        if (gameDataLayer.checkFenceCollision(userSnake)) {
            stopGame();
            gameGraphicLayer.gameOver();
        }
        gameDataLayer.checkFoodCollision(userSnake);
        userSnake.move();
    }

    private void initStartPosition() {
        ppi = unitMaker.makePpi();
        rabbit = unitMaker.makeRabbit();
        userSnake = unitMaker.makeUserSnake();
        rabbit.setPosition(new Position(250, 250));
        ppi.setPosition(new Position(350, 450));
        userSnake.setPosition(new Position(550, 450));
        userSnake.incrementBody(unitResourceManager);
    }

    public void runGame() {
        initStartPosition();

        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
        gameGraphicLayer.addUserKeyListener(gameKeyAdapter);
        gameKeyAdapter.setKeyListener(userSnake);

        gameGraphicLayer.run();

        timer = new Timer(gameDataLayer.getDELAY(), e -> {
            if (gameDataLayer.isInGame()) {
                gameProcess();
            }
            if (!gameDataLayer.isInGame()) {
                stopGame();
            }
        });
        timer.start();
    }

    private void stopGame() {
        timer.stop();
    }

    public GameGraphicLayer getGameGraphicLayer() {
        return gameGraphicLayer;
    }
}
