package com.dotnet.character;

import com.dotnet.Position;

import java.awt.*;

public class Unit {
    private String name;
    private final Image img;
    private final Position p;
    private int width;
    private int height;

    public Unit(String name, Image image, int width, int height) {
        this.name = name;
        img = image;
        this.p = new Position();
        this.width = width;
        this.height = height;
    }

    public boolean isName(String name) {
        return this.name.equals(name);
    }

    public void setPosition(Position p) {
        this.p.setPosition(p);
    }

    public Position getPoint() {
        return p;
    }

    public Image getImg() {
        return img;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public boolean checkCollision(Unit unit){
        Position target = unit.getPoint();
        return p.equalPos(target);
    }
}