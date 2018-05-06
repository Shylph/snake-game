package com.dotnet.character;

import com.dotnet.Position;

import javax.swing.*;

public class Unit {
    private String name;
    private final ImageIcon img;

    private CollisionArea collisionArea;

    public Unit(String name, ImageIcon image) {
        this.name = name;
        img = image;
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

    public Position getDrawPosition() {
        return collisionArea.getDrawPosition();
    }

    public ImageIcon getImgIcon() {
        return img;
    }

    /*public int getWidth() {
        return img.getIconWidth();
    }

    public int getHeight() {
        return img.getIconHeight();
    }*/

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