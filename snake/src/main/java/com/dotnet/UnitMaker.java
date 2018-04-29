package com.dotnet;

import com.dotnet.character.Ppi;
import com.dotnet.character.Rabbit;
import com.dotnet.character.Unit;
import com.dotnet.character.snake.UserSnake;

public class UnitMaker {

    private final DrawResourceManager drawResourceManager;

    public UnitMaker(DrawResourceManager drawResourceManager) {
        this.drawResourceManager = drawResourceManager;

    }

    UserSnake makeUserSnake() {
        UserSnake userSnake = new UserSnake();
        drawResourceManager.addUnit(userSnake.getDrawResource());
        return userSnake;
    }

    Unit makeRabbit() {
        Rabbit rabbit = new Rabbit();
        drawResourceManager.addUnit(rabbit.getDrawResource());
        return rabbit;
    }

    Unit makePpi() {
        Ppi ppi = new Ppi();
        drawResourceManager.addUnit(ppi.getDrawResource());
        return ppi;
    }
}