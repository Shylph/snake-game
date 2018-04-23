package com.dotnet.character;

import com.dotnet.DrawResource;
import com.dotnet.Position;
import com.dotnet.imageSource.ImageSource;

public class Rabbit {
    private Position p;
    private DrawResource drawResource;

    public Rabbit() {
        p = new Position();
        drawResource = new DrawResource(ImageSource.getRabbitImg(), p, 60, 60);
    }

    public int getX() {
        return p.getX();
    }

    public int getY() {
        return p.getY();
    }

    public void setX(int x) {
        this.p.setX(x);
    }

    public void setY(int y) {
        this.p.setY(y);
    }

    public DrawResource getDrawResource() {
        return drawResource;
    }
}
