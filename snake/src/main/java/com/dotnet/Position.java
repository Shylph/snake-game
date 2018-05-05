package com.dotnet;

import static java.lang.Math.sqrt;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        setPosition(x, y);
    }

    public Position(Position p) {
        setPosition(p);
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setPosition(Position position) {
        x = position.getX();
        y = position.getY();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void left(int term) {
        x = x - term;
    }

    public void right(int term) {
        x = x + term;
    }

    public void up(int term) {
        y = y - term;
    }

    public void down(int term) {
        y = y + term;
    }

    public Position diff(Position p) {
        return new Position(x - p.getX(), y - p.getY());
    }

    public Position add(Position p) {
        x += p.getX();
        y += p.getY();
        return this;
    }

    public double lengthFromZero(){
        return sqrt(x*x+y*y);
    }

    public Position clone() {
        return new Position(x, y);
    }

    public boolean equalPos(Position p) {
        return (x == p.getX()) & (y == p.getY());
    }

}
