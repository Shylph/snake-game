package com.dotnet.character.snake;

import com.dotnet.Position;
import com.dotnet.DrawResource;
import com.dotnet.imageSource.ImageSource;

import java.util.ArrayList;
import java.util.List;

import static com.dotnet.character.snake.Snake.Direction.*;


public class Snake {
    private final int DOT_SIZE = 50;
    private ArrayList<DrawResource> snakeResources;
    private ArrayList<Position> positions;
    private int length;
    private Direction direction=DOWN;
    private int bodyWidth;
    private int bodyHeight;

    public Snake() {
        initSnake();
    }

    private void initSnake() {
        length=1;
        positions = new ArrayList<>();
        positions.add(new Position());
        snakeResources = new ArrayList<>();
        int headWidth = 55;
        int headHeight = 70;
        snakeResources.add(new DrawResource(ImageSource.getHeadImg(), positions.get(0) , headWidth, headHeight));
        bodyWidth = 50;
        bodyHeight = 50;

        positions.get(0).setPosition(150  , 150);
        addBody();
        addBody();
        addBody();
    }

    private void addBody(){
        Position position = new Position();
        position.setPosition(positions.get(positions.size()-1));
        positions.add(position);
        snakeResources.add(new DrawResource(ImageSource.getP1_body(), position, bodyWidth, bodyHeight));
        length++;
    }


/*
    public void incrementDots() {
        dots++;
    }
*/

    public int getDOT_SIZE() {
        return DOT_SIZE;
    }

    public List<DrawResource> getDrawResource() {
        return snakeResources;
    }
    void down() {
        direction=DOWN;
    }

    void up() {
        direction=UP;
    }

    void right() {
        direction=RIGHT;
    }

    void left() {
        direction=LEFT;
    }

    public void move() {
        for (int z = length-1; z > 0; z--) {
            positions.get(z).setPosition(positions.get(z - 1));
        }

        if (direction == LEFT) {
            positions.get(0).left(getDOT_SIZE());
        }

        if (direction == RIGHT) {
            positions.get(0).right(getDOT_SIZE());
        }

        if (direction == UP) {
            positions.get(0).up(getDOT_SIZE());
        }

        if (direction == DOWN) {
            positions.get(0).down(getDOT_SIZE());
        }
    }
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}

