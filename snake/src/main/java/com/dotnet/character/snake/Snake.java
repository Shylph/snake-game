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
    private int speed;

    public Snake() {
        initSnake();
    }

    private void initSnake() {
        length=1;
        speed = 8;
        positions = new ArrayList<>();
        positions.add(new Position());
        snakeResources = new ArrayList<>();
        int headWidth = 60;
        int headHeight = 80;
        snakeResources.add(new DrawResource(ImageSource.getHeadImg(), positions.get(0) , headWidth, headHeight));
        bodyWidth = 50;
        bodyHeight = 50;

        positions.get(0).setPosition(150  , 150);
        addBody();
        addBody();
        addBody();
        addBody();
        addBody();
        addBody();
        addBody();
        addBody();
        addBody();
        addBody();
    }

    private void addBody(){
        Position position = new Position();
        position.setPosition(positions.get(positions.size()-1));
        position.left(getDOT_SIZE());
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
       /* for (int z = length-1; z > 0; z--) {
            positions.get(z).setPosition(positions.get(z - 1));
        }
*/
       Position prePos = positions.get(0).clone();
        if (direction == LEFT) {
            positions.get(0).left(speed);
        }else if (direction == RIGHT) {
            positions.get(0).right(speed);
        }else if (direction == UP) {
            positions.get(0).up(speed);
        }else if (direction == DOWN) {
            positions.get(0).down(speed);
        }

       for(int i=1;i<positions.size();i++){
            Position pos = positions.get(i);
            int m = DOT_SIZE-speed;
            int n = speed;
            Position nextPos = new Position((m*pos.getX()+n*prePos.getX())/DOT_SIZE,(m*pos.getY()+n*prePos.getY())/DOT_SIZE);
            prePos.setPosition(pos);
            pos.setPosition(nextPos);
       }
    }
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}

