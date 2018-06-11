package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.Snake;

import java.util.List;

public class GameDataLayer {
    private final UnitResourceManager unitResourceManager;
    private boolean inGame = true;
    private final int DELAY = 50;
    private int bgBoundary[][] = {{755, 243, 1402, 164}, {850, 480, 1100, 254}, {850, 500, 1520, 80}};
    private int bgSelecter;
    private ScoreBoard scoreBoard;


    GameDataLayer(UnitResourceManager unitResourceManager, ScoreBoard scoreBoard) {
        this.unitResourceManager = unitResourceManager;
        this.scoreBoard = scoreBoard;
    }

    public int[] getCurrentBgBoundary() {
        return bgBoundary[bgSelecter];
    }

    public boolean isInGame() {
        return inGame;
    }

    public int getDELAY() {
        return DELAY;
    }

    public boolean checkFenceCollision(Unit unit) {
        boolean collision = false;
        Position pos = unit.getPoint();
        if (pos.getY() >= bgBoundary[bgSelecter][0]) {
            collision = true;
        }

        if (pos.getY() < bgBoundary[bgSelecter][1]) {
            collision = true;
        }

        if (pos.getX() >= bgBoundary[bgSelecter][2]) {
            collision = true;
        }

        if (pos.getX() < bgBoundary[bgSelecter][3]) {
            collision = true;
        }
        return collision;
    }

    public boolean checkFoodCollision(Snake snake) {
        List<Unit> foods = unitResourceManager.getFoodUnit();
        for (Unit food : foods) {
            if (snake.checkCollision(food)) {
                snake.incrementBody(unitResourceManager);
                unitResourceManager.removeUnit(food.getName());
                scoreBoard.addScore(100);
                return true;
            }
        }
        return false;
    }

    public void changeFenceBoundary(int stage) {
        bgSelecter = stage;
    }


}
