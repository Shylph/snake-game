package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.UserSnake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
        if(gameDataLayer.checkFoodCollision(userSnake)){
            Random random = new Random();
            rabbit = unitMaker.makeRabbit(new Position(random.nextInt(1600), random.nextInt(900)));
        }
        userSnake.move();
    }

    private void initStartPosition() {
        ppi = unitMaker.makePpi(new Position(350, 450));
        userSnake = unitMaker.makeUserSnake(new Position(550, 450));
        rabbit = unitMaker.makeRabbit(new Position(250, 250));
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
