package com.dotnet.character;

import com.dotnet.Position;
import com.dotnet.UnitResourceManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import static com.dotnet.character.Direction.*;


public class Snake extends Unit implements Movable {
    private final int DOT_SIZE = 50;
    private ArrayList<Unit> snakeResources;
    private Direction direction = LEFT;
    private int speed;
    private String bodyPath;
    private KeyListener keyListener;

    public Snake(String name, String headPath, String bodyPath) {
        super(name, headPath);
        this.bodyPath = bodyPath;
        Position boundary[] = {new Position(7, -54),
                new Position(27, -24),
                new Position(28, -6),
                new Position(17, 20),
                new Position(11, 23),
                new Position(-12, 23),
                new Position(-25, 11),
                new Position(-29, -2),
                new Position(-29, -17),
                new Position(-21, -38),
                new Position(-3, -54)};
        setCollisionArea(new CollisionArea(new Position(30, 55), boundary));

        initSnake();

    }

    private void initSnake() {
        speed = 20;
        snakeResources = new ArrayList<>();

        snakeResources.add(this);
    }

    public void incrementBody(UnitResourceManager unitResourceManager) {
        Unit tail = new Unit(name, bodyPath);
        //바운더리 몸체에 맞게 새로 만들어야 함
        Position boundary[] = {new Position(7, -54),
                new Position(27, -24),
                new Position(28, -6),
                new Position(17, 20),
                new Position(11, 23),
                new Position(-12, 23),
                new Position(-25, 11),
                new Position(-29, -2),
                new Position(-29, -17),
                new Position(-21, -38),
                new Position(-3, -54)};
        tail.setCollisionArea(new CollisionArea(new Position(30, 30), boundary));
        tail.setPosition(snakeResources.get(snakeResources.size() - 1).getPoint());
        snakeResources.add(tail);
        unitResourceManager.addUnit(tail);
    }

    public List<Unit> getDrawResource() {
        return snakeResources;
    }

    void down() {
        direction = DOWN;
        setRotation(180);
    }

    void up() {
        direction = UP;
        setRotation(0);
    }

    void right() {
        direction = RIGHT;
        setRotation(90);
    }

    void left() {
        direction = LEFT;
        setRotation(270);
    }

    @Override
    public void move() {
        Position prePos = getPoint().clone();
        if (direction == LEFT) {
            getPoint().left(speed);
        } else if (direction == RIGHT) {
            getPoint().right(speed);
        } else if (direction == UP) {
            getPoint().up(speed);
        } else if (direction == DOWN) {
            getPoint().down(speed);
        }

        for (int i = 1; i < snakeResources.size(); i++) {
            Position pos = snakeResources.get(i).getPoint();
            int m = DOT_SIZE - speed;
            int n = speed;
            Position nextPos = new Position((m * pos.getX() + n * prePos.getX()) / DOT_SIZE, (m * pos.getY() + n * prePos.getY()) / DOT_SIZE);
            prePos.setPosition(pos);
            pos.setPosition(nextPos);
        }
    }

    public void setAllPosition(Position position) {
        for (Unit unit : snakeResources) {
            unit.setPosition(position);
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void setKey(int upKeyCode, int downKeyCode, int leftKeyCode, int rightKeyCode) {
        this.keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == leftKeyCode)) {
                    left();
                } else if (key == rightKeyCode) {
                    right();
                } else if ((key == upKeyCode)) {
                    up();
                } else if ((key == downKeyCode)) {
                    down();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }


    public boolean checkBodyCollision(Unit unit) {
        boolean result = false;
        for (int i = 1; i < snakeResources.size(); i++) {
            Unit body = snakeResources.get(i);
            if (unit.checkCollision(body)) {
                return true;
            }
            /*
            if(body.getPoint().equalPos(getPoint()))
                return false;*/
        }
        return result;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

