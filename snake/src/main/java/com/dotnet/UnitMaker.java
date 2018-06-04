package com.dotnet;

import com.dotnet.character.*;
import com.dotnet.character.snake.Snake;
import com.dotnet.character.snake.UserSnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnitMaker {

    private final UnitResourceManager unitResourceManager;
    private List<Movable> movables;

    UnitMaker(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;
        movables = new ArrayList<>();
    }

    UserSnake makeUserSnake(Position startPos) {
        UserSnake userSnake = new UserSnake("res/head1_new.png","res/body1_new.png");
        unitResourceManager.addUnit(userSnake.getDrawResource());
        userSnake.setPosition(startPos);
        movables.add(userSnake);
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
    SnakeHunter makeSnakeHunter(Position startPos) {
        SnakeHunter snakeHunter= new SnakeHunter();
        unitResourceManager.addUnit(snakeHunter.getDrawResource());
        snakeHunter.setPosition(startPos);
        movables.add(snakeHunter);
        return snakeHunter;
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

    public List<Movable> getMovables() {
        return movables;
    }
}