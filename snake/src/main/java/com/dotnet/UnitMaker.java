package com.dotnet;

import com.dotnet.character.Ppi;
import com.dotnet.character.Rabbit;
import com.dotnet.character.Unit;
import com.dotnet.character.snake.UserSnake;

public class UnitMaker {

    private final UnitResourceManager unitResourceManager;

    public UnitMaker(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;

    }

    UserSnake makeUserSnake() {
        UserSnake userSnake = new UserSnake();
        unitResourceManager.addUnit(userSnake.getDrawResource());
        return userSnake;
    }

    Unit makeRabbit() {
        Rabbit rabbit = new Rabbit();
        unitResourceManager.addUnit(rabbit.getDrawResource());
        return rabbit;
    }

    Unit makePpi() {
        Ppi ppi = new Ppi();
        unitResourceManager.addUnit(ppi.getDrawResource());
        return ppi;
    }
}