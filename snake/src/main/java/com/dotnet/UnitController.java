package com.dotnet;

import com.dotnet.character.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnitController {

    private final UnitResourceManager unitResourceManager;
    private List<Movable> movables;
    private List<Snake> snakeList;
    private List<Unit> foodAlphabetList;
    private int seed;

    UnitController(UnitResourceManager unitResourceManager) {
        this.unitResourceManager = unitResourceManager;
        movables = new ArrayList<>();
        snakeList = new ArrayList<>();
        foodAlphabetList = new ArrayList<>();
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

    Snake makeAiSnake(Position startPos) {
        AiSnake snake = null;
        snake = new AiSnake("aiSnake", "res/head2_new.png", "res/body2_new.png");
        unitResourceManager.addUnit(snake.getDrawResource());
        snake.setPosition(startPos);
        movables.add(snake);
        snakeList.add(snake);
        return snake;
    }

    private Unit makeRabbit(Position startPos) {
        Rabbit rabbit = new Rabbit();
        unitResourceManager.addUnit(rabbit.getDrawResource());
        rabbit.setPosition(startPos);
        foodAlphabetList.add(rabbit);
        return rabbit;
    }

    private Unit makePpi(Position startPos) {
        Ppi ppi = new Ppi();
        unitResourceManager.addUnit(ppi.getDrawResource());
        ppi.setPosition(startPos);
        foodAlphabetList.add(ppi);
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
        foodAlphabetList.add(unit);
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
        if (unit != null) {
            unitResourceManager.removeUnits(unit.getName());
            for (Snake snake : snakeList) {
                if (unit.getName().equals(snake.getName())) {
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

    public Alphabet makeAlphabet(Position startPos) {
        Random random = new Random();
        char name = (char) (random.nextInt(25) + 65);

        Alphabet alphabet = new Alphabet(name);
        unitResourceManager.addUnit(alphabet.getDrawResource());
        alphabet.setPosition(startPos);
        foodAlphabetList.add(alphabet);
        return alphabet;
    }

    public Alphabet makeAlphabet(char name, Position startPos) {
        Alphabet alphabet = new Alphabet(name);
        unitResourceManager.addUnit(alphabet.getDrawResource());
        alphabet.setPosition(startPos);
        foodAlphabetList.add(alphabet);
        return alphabet;
    }

    public void removeAllFoodAlphabet(){
        for(Unit unit :  foodAlphabetList){
            unitResourceManager.removeUnit(unit.getName());
        }
    }
}