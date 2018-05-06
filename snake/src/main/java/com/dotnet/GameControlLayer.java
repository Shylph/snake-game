package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.Snake;
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
    private SnakeAi snakeAi;

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
            rabbit = unitMaker.makeFood(new Position(random.nextInt(1250)+150, random.nextInt(490)+300));
        }
        userSnake.move();
        snakeAi.move();
    }

    private void initStartPosition() {
        ppi = unitMaker.makePpi(new Position(350, 450));
        userSnake = unitMaker.makeUserSnake(new Position(550, 450));
        rabbit = unitMaker.makeRabbit(new Position(250, 350));
        Snake snake = unitMaker.makeSnake(new Position(1000, 650));
        snakeAi = new SnakeAi(snake);
       // userSnake.incrementBody(unitResourceManager);
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
