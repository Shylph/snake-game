package com.dotnet.character;

import com.dotnet.Position;

import java.awt.*;

public class Unit {
    private String name;
    private final Image img;

    private CollisionArea collisionArea;
    private int width;
    private int height;

    public Unit(String name, Image image, int width, int height) {
        this.name = name;
        img = image;
        this.width = width;
        this.height = height;
        this.collisionArea = new CollisionArea(null, null);
    }

    public boolean isName(String name) {
        return this.name.equals(name);
    }

    public void setPosition(Position p) {
        collisionArea.setPosition(p);
    }

    public Position getPoint() {
        return collisionArea.getPosition();
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

    private CollisionArea getCollisionArea() {
        return collisionArea;
    }

    public void setCollisionArea(CollisionArea collisionArea) {
        this.collisionArea = collisionArea;
    }

    public String getName() {
        return name;
    }

    public boolean checkCollision(Unit unit) {
        CollisionArea targetArea = unit.getCollisionArea();
        return collisionArea.checkCollision(targetArea);
    }
}