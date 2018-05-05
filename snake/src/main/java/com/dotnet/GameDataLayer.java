package com.dotnet;

import com.dotnet.character.Unit;
import com.dotnet.character.snake.Snake;

import java.util.List;

public class GameDataLayer {
    private final UnitResourceManager unitResourceManager;
    private boolean inGame = true;
    private final int DELAY = 50;

    public GameDataLayer(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;
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
        foods.addAll(unitResourceManager.getUnitResources("rabbit"));
        for (Unit food : foods) {
            if (snake.checkCollision(food)) {
                snake.incrementBody(unitResourceManager);
                unitResourceManager.removeUnit(food.getName());
                return true;
            }
        }
        return false;
    }
}
