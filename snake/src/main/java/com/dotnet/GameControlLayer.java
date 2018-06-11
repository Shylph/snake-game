package com.dotnet;

import com.dotnet.character.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameControlLayer {
    private Timer timer;
    private GameGraphicLayer gameGraphicLayer;
    private UnitMaker unitMaker;
    private GameDataLayer gameDataLayer;
    private final ScoreBoard scoreBoard;
    private int stage;
    private GameMode gameMode;
    private final SoundController soundController;
    private SnakeHunter snakeHunter;
    private Fireman fireman;


    GameControlLayer() {
        soundController = new SoundController();
        UnitResourceManager unitResourceManager = new UnitResourceManager();
        unitMaker = new UnitMaker(unitResourceManager);
        scoreBoard = new ScoreBoard();
        gameGraphicLayer = new GameGraphicLayer(unitResourceManager, scoreBoard);

        gameDataLayer = new GameDataLayer(unitResourceManager, scoreBoard);

        ScreenConfig screenConfig = new ScreenConfig();
        gameGraphicLayer.setPreferredSize(new Dimension(screenConfig.getWidth(), screenConfig.getHeight()));
        stage = 0;
    }

    private void gameProcess() {
        if (gameMode == GameMode.ARCADE) {
            if (scoreBoard.getScore() > 400 && stage == 1) {
                nextStage();
                generateFood();
                generateAlphabet();
                fireman = unitMaker.makeFireman(new Position(700, 650));
            } else if (scoreBoard.getScore() > 200 && stage == 0) {
                nextStage();
                generateFood();
                generateAlphabet();
                snakeHunter = unitMaker.makeSnakeHunter(new Position(1000, 650));
            }
        }
        Snake[] snakeList = unitMaker.getSnakeList();
        for (Snake snake : snakeList) {
            if (gameDataLayer.checkFenceCollision(snake)) {
                gameOverProcess();
            }
            if (gameDataLayer.checkFoodCollision(snake)) {
                generateFood();
            }
            if (gameDataLayer.checkAlphabetCollision(snake)) {
                generateAlphabet();
            }
            if (snakeHunter != null && snakeHunter.checkCollision(snake)) {
                gameOverProcess();
            }
            if (fireman != null && fireman.checkCollision(snake)) {
                gameOverProcess();
            }
        }
        if (gameMode == GameMode.FIGHT && snakeList.length > 1) {

            Unit loser = null;

            Snake snake1 = snakeList[0];
            Snake snake2 = snakeList[1];
            if (snake1.checkBodyCollision(snake2)) {
                loser = snake2;
            }
            if (snake2.checkBodyCollision(snake1)) {
                loser = snake1;
            }
            unitMaker.removeUnit(loser);
        }

        for (Movable movable : unitMaker.getMovables()) {
            movable.move();
        }
    }

    private void gameOverProcess() {
        stopGame();
        gameGraphicLayer.gameOver();
        soundController.playGameOver();
        soundController.stop_background();
    }

    private void generateFood() {
        Random random = new Random();
        int boundary[] = gameDataLayer.getCurrentBgBoundary();
        unitMaker.makeFood(new Position(random.nextInt(boundary[2] - boundary[3]) + boundary[3], random.nextInt(boundary[0] - boundary[1]) + boundary[1]));
    }

    private void generateAlphabet() {
        Random random = new Random();
        int boundary[] = gameDataLayer.getCurrentBgBoundary();
        unitMaker.makeAlphabet(new Position(random.nextInt(boundary[2] - boundary[3]) + boundary[3], random.nextInt(boundary[0] - boundary[1]) + boundary[1]));
    }

    private void nextStage() {
        stage++;
        for (Snake snake : unitMaker.getSnakeList()) {
            snake.setAllPosition(new Position(550, 600));
        }
        gameGraphicLayer.changeBackground(stage);
        gameDataLayer.changeFenceBoundary(stage);
        soundController.playBackground(stage);
        soundController.playNextStage();
    }

    public void runArcadeGame() {
        gameGraphicLayer.setScoreDisplayFlag(true);
        gameMode = GameMode.ARCADE;
        Snake snake = unitMaker.makeSnake(new Position(550, 600));
        generateFood();
        generateAlphabet();

        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
        snake.setKey(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        gameKeyAdapter.addKeyListener(snake.getKeyListener());
        gameGraphicLayer.setUserKeyAdapter(gameKeyAdapter);

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
        soundController.playBackground(stage);
    }

    private void stopGame() {
        timer.stop();
    }

    public GameGraphicLayer getGameGraphicLayer() {
        return gameGraphicLayer;
    }

    public void runFightGame() {
        gameGraphicLayer.setScoreDisplayFlag(false);
        gameMode = GameMode.FIGHT;
        Snake snake1 = unitMaker.makeSnake(new Position(550, 600));
        Snake snake2 = unitMaker.makeSnake(new Position(700, 600));
        generateFood();

        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
        snake1.setKey(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        gameKeyAdapter.addKeyListener(snake1.getKeyListener());
        snake2.setKey(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        gameKeyAdapter.addKeyListener(snake2.getKeyListener());

        gameGraphicLayer.setUserKeyAdapter(gameKeyAdapter);

        gameGraphicLayer.run();

        timer = new Timer(gameDataLayer.getDELAY(), e -> {
            if (gameDataLayer.isInGame()) {
                gameProcess();
            }

        });
        timer.start();
        soundController.playBackground(stage);
    }

    enum GameMode {
        ARCADE,
        FIGHT
    }
}
