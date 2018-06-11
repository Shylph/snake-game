package com.dotnet;

import com.dotnet.character.*;
import com.dotnet.character.snake.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnitMaker {

    private final UnitResourceManager unitResourceManager;
    private List<Movable> movables;
    private List<Snake> snakeList;
    private int seed;

    UnitMaker(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;
        movables = new ArrayList<>();
        snakeList = new ArrayList<>();
        seed = 0;
    }

    Snake makeSnake(Position startPos) {
        Snake snake = null;
        if (seed == 0)
            snake = new Snake("snake1", "res/head1_new.png", "res/body1_new.png");
        else if (seed == 1)
            snake = new Snake("snake2", "res/head2_new.png", "res/body2_new.png");
        seed++;
        unitResourceManager.addUnit(snake.getDrawResource());
        snake.setPosition(startPos);
        movables.add(snake);
        snakeList.add(snake);
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
        SnakeHunter snakeHunter = new SnakeHunter();
        unitResourceManager.addUnit(snakeHunter.getDrawResource());
        snakeHunter.setPosition(startPos);
        movables.add(snakeHunter);
        return snakeHunter;
    }

    Unit makeFood(Position startPos) {
        Random random = new Random();
        int i = random.nextInt(2);
        Unit unit;
        if (i == 0) {
            unit = makeRabbit(startPos);
        } else {
            unit = makePpi(startPos);
        }

        return unit;
    }

    public Movable[] getMovables() {
        Movable[] temp = new Movable[movables.size()];
        return movables.toArray(temp);
    }

    public Snake[] getSnakeList() {
        Snake[] temp = new Snake[snakeList.size()];
        return snakeList.toArray(temp);
    }


    public void removeUnit(Unit unit) {
        if(unit!=null){
            unitResourceManager.removeUnit(unit.getName());
            for(Snake snake : snakeList){
                if(unit.getName().equals(snake.getName())){
                    movables.remove(snake);
                    snakeList.remove(snake);
                    break;
                }
            }
        }
    }

    public Fireman makeFireman(Position startPos) {
        Fireman fireman = new Fireman();
        unitResourceManager.addUnit(fireman.getDrawResource());
        fireman.setPosition(startPos);
        movables.add(fireman);
        return fireman;

    }
}