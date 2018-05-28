package com.dotnet;

import com.dotnet.character.Ppi;
import com.dotnet.character.Rabbit;
import com.dotnet.character.Unit;
import com.dotnet.character.snake.Snake;
import com.dotnet.character.snake.UserSnake;

import java.util.Random;

public class UnitMaker {

    private final UnitResourceManager unitResourceManager;

    public UnitMaker(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;
    }

    UserSnake makeUserSnake(Position startPos) {
        UserSnake userSnake = new UserSnake("res/head1_new.png","res/body1_new.png");
        unitResourceManager.addUnit(userSnake.getDrawResource());
        userSnake.setPosition(startPos);
        return userSnake;
    }
    Snake makeSnake(Position startPos){
        Snake snake = new Snake("res/head2_new.png","res/body2_new.png");
        unitResourceManager.addUnit(snake.getDrawResource());
        snake.setPosition(startPos);
        return snake;
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

    Unit makeFood(Position startPos){
        Random random = new Random();
        int i = random.nextInt(2);
        Unit  unit;
        if(i == 0){
            unit =makeRabbit(startPos);
        }else{
            unit = makePpi(startPos);
        }

        return unit;
    }
}