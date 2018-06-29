package com.dotnet;

import com.dotnet.character.Snake;
import com.dotnet.character.Unit;

import java.util.ArrayList;
import java.util.List;

public class GameDataLayer {
    private final UnitResourceManager unitResourceManager;
    private boolean inGame = true;
    private final int DELAY = 50;
    private int bgBoundary[][] = {{755, 243, 1402, 164}, {850, 480, 1100, 254}, {850, 500, 1520, 80}};
    private int bgSelecter;
    private ScoreBoardManager scoreBoardManager;


    GameDataLayer(UnitResourceManager unitResourceManager, ScoreBoardManager scoreBoardManager) {
        this.unitResourceManager = unitResourceManager;
        this.scoreBoardManager = scoreBoardManager;
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
        List<Unit> foods = unitResourceManager.getUnitResources("ppi");
        foods.addAll(unitResourceManager.getUnitResources("rabbit"));
        for (Unit food : foods) {
            if (snake.checkCollision(food)) {
                snake.incrementBody(unitResourceManager);
                unitResourceManager.removeUnits(food.getName());
                scoreBoardManager.getBoard(snake.getName()).addScore(100);
                return true;
            }
        }
        return false;
    }

    public void changeFenceBoundary(int stage) {
        bgSelecter = stage;
    }


    public boolean checkAlphabetCollision(Snake snake) {
        List<Unit> alphabets = new ArrayList<>();
        for (char i = 65; i < 91; i++) {
            List<Unit> temp = unitResourceManager.getUnitResources(String.valueOf(i));
            if (temp != null) {
                alphabets.addAll(temp);
            }
        }
        for(Unit alphabet : alphabets){
            if (snake.checkCollision(alphabet)) {
                unitResourceManager.removeUnit(alphabet.getName());
                scoreBoardManager.getBoard(snake.getName()).addAlphabet(alphabet.getName());
                return true;
            }
        }
        return false;
    }
}
