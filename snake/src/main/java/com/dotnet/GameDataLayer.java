package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.Snake;
import com.dotnet.character.snake.UserSnake;

import java.util.List;

public class GameDataLayer {
    private final UnitResourceManager unitResourceManager;
    private boolean inGame = true;
    private final int RAND_POS = 29;
    private final int DELAY = 50;

    public GameDataLayer(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void falseInGame() {
        inGame = false;
    }

    public void trueInGame() {
        inGame = true;
    }

    public int getDELAY() {
        return DELAY;
    }

    public int getRAND_POS() {
        return RAND_POS;
    }

    public boolean checkFenceCollision(Unit unit) {
        boolean collision = false;
        Position pos = unit.getPoint();
        if (pos.getY() >= 755) {
            collision = true;
        }

        if (pos.getY() < 243) {
            collision = true;
        }

        if (pos.getX() >= 1402) {
            collision = true;
        }

        if (pos.getX() < 164) {
            collision = true;
        }
        return collision;
    }

    public boolean checkFoodCollision(Snake snake) {
        List<Unit> foods = unitResourceManager.getUnitResources("ppi");
        for (Unit food : foods) {
            if(snake.checkCollision(food)){
                snake.incrementBody(unitResourceManager);
                unitResourceManager.removeUnit(food.getName());
                return true;
            }
        }
        return false;
    }

   /*  public void checkCollision() {
        for (int z = snake.getDots(); z > 0; z--) {
            if ((z > 4) && (snake.getX()[0] == snake.getX()[z]) && (snake.getY()[0] == snake.getY()[z])) {
                tempInGame = false;
            }
        }


        if(!tempInGame)
            falseInGame();
    }*/
}
