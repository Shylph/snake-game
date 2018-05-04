package com.dotnet.character;

import com.dotnet.Position;

import java.awt.*;

public class Unit {
    private final Image img;
    private final Position p;
    private int width;
    private int height;
    public Unit(Image image,  int width, int height){
        img=image;
        this.p = new Position();
        this.width = width;
        this.height = height;
    }

    public void setPosition(Position p){
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
}