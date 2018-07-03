package com.dotnet;

import com.dotnet.character.*;
import com.dotnet.util.CSVUtil;
import com.dotnet.vo.ObservationAndReward;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameControlLayer {
    private final CSVUtil csvUtil;
    private Timer timer;
    private GameGraphicLayer gameGraphicLayer;
    private UnitController unitController;
    private GameDataLayer gameDataLayer;
    private final ScoreBoardManager scoreBoardManager;
    private int stage;
    private GameMode gameMode;
    private final SoundController soundController;
    private SnakeHunter snakeHunter;
    private Fireman fireman;


    GameControlLayer() {
        soundController = new SoundController();
        UnitResourceManager unitResourceManager = new UnitResourceManager();
        unitController = new UnitController(unitResourceManager);
        this.scoreBoardManager = new ScoreBoardManager();
        gameGraphicLayer = new GameGraphicLayer(unitResourceManager, scoreBoardManager);

        gameDataLayer = new GameDataLayer(unitResourceManager, scoreBoardManager);

        ScreenConfig screenConfig = new ScreenConfig();
        gameGraphicLayer.setPreferredSize(new Dimension(screenConfig.getWidth(), screenConfig.getHeight()));
        stage = 0;
        csvUtil = new CSVUtil("state", "./state");
    }

    private void gameProcess() {
        if (gameMode == GameMode.ARCADE) {
            if (scoreBoardManager.getBoardAnyOne().getScore() > 400 && stage == 1) {
                nextStage();
                unitController.removeAllFoodAlphabet();
                generateFood();
                generateAlphabet();
                fireman = unitController.makeFireman(new Position(700, 650));
            } else if (scoreBoardManager.getBoardAnyOne().getScore() > 200 && stage == 0) {
                nextStage();
                unitController.removeAllFoodAlphabet();
                generateFood();
                generateAlphabet();
                snakeHunter = unitController.makeSnakeHunter(new Position(1000, 650));
            }
        }
        Snake[] snakeList = unitController.getSnakeList();
        for (Snake snake : snakeList) {
            if (gameDataLayer.checkFenceCollision(snake)) {
                scoreBoardManager.getBoard(snake.getName()).addScore(-10000);
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
            unitController.removeUnit(loser);
        }

        for (Snake snake : unitController.getSnakeList()) {

            if (snake.isName("aiSnake")) {
                snake.move();
                ObservationAndReward observationAndReward = gameDataLayer.getObservationAndReward();
                observationAndReward.setAction(snake.getDirection());
                csvUtil.appendCSV(observationAndReward);
                ((AiSnake)snake).saveRawData(observationAndReward.getRawData());
            }else{
                snake.move();
            }
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
        unitController.makeFood(new Position(random.nextInt(boundary[2] - boundary[3]) + boundary[3], random.nextInt(boundary[0] - boundary[1]) + boundary[1]));
    }

    private void generateAlphabet() {
        Random random = new Random();
        int boundary[] = gameDataLayer.getCurrentBgBoundary();
        unitController.makeAlphabet(new Position(random.nextInt(boundary[2] - boundary[3]) + boundary[3], random.nextInt(boundary[0] - boundary[1]) + boundary[1]));
    }

    private void nextStage() {
        stage++;
        for (Snake snake : unitController.getSnakeList()) {
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
        Snake snake = unitController.makeSnake(new Position(550, 600));
        scoreBoardManager.register(snake.getName());
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
        Snake snake1 = unitController.makeSnake(new Position(550, 600));
        snake1.setSpeed(0);
        scoreBoardManager.register(snake1.getName());
//        Snake snake2 = unitController.makeSnake(new Position(700, 600));
        Snake snake2 = unitController.makeAiSnake(new Position(700, 600));
        scoreBoardManager.register(snake2.getName());
        generateFood();

        GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
//        snake1.setKey(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
//        gameKeyAdapter.addKeyListener(snake1.getKeyListener());
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
