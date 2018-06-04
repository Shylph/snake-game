package com.dotnet.character;

import com.dotnet.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Unit {
    private String name;
    private CollisionArea collisionArea;
    private BufferedImage bufferedImage;
    private double angle;

    public Unit(String name, String filePath) {
        this.name = name;
        this.collisionArea = new CollisionArea(null, null);
        angle = 0;
        try {
            bufferedImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public BufferedImage getBufferedImage() {
        Position centralAxis = collisionArea.getCentralAxis();
        double locationX = centralAxis.getX();
        double locationY = centralAxis.getY();

        AffineTransform tx = AffineTransform.getRotateInstance(angle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(bufferedImage, null);
    }

    protected void setRotation(int angle) {
        this.angle = Math.toRadians(angle);
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