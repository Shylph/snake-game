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

    UserSnake makeUserSnake(Position startPos) {
        UserSnake userSnake = new UserSnake();
        unitResourceManager.addUnit(userSnake.getDrawResource());
        userSnake.setPosition(startPos);
        return userSnake;
    }

    Unit makeRabbit(Position startPos) {
        Rabbit rabbit = new Rabbit();
        unitResourceManager.addUnit(rabbit.getDrawResource());
        rabbit.setPosition(startPos);
        return rabbit;
    }

    Unit makePpi(Position startPos) {
        Ppi ppi = new Ppi();
        unitResourceManager.addUnit(ppi.getDrawResource());
        ppi.setPosition(startPos);

        return ppi;
    }
}