package com.dotnet.character;

import com.dotnet.Position;

import javax.swing.*;

public class Rabbit extends Unit {

    public Rabbit() {
        super("rabbit", "res/Rabbit_1.png");
        Position boundary[] = {new Position(-5, -23),
                new Position(27, -31),
                new Position(8, -11),
                new Position(24, 1),
                new Position(34, -5),
                new Position(34, 7),
                new Position(20, 19),
                new Position(-7, 20),
                new Position(-14, 6),
                new Position(-25, -6),
                new Position(-18, -20),};
        setCollisionArea(new CollisionArea(new Position(25, 35), boundary));
    }

    public Unit getDrawResource() {
        return this;
    }
}
