package com.dotnet.character;

import com.dotnet.Position;

import javax.swing.*;

public class Ppi extends Unit {
    public Ppi() {
        super("ppi", "res/ppi_1.png");

        Position boundary[] = {new Position(-13, -18),
                new Position(-4, -18),
                new Position(4, -14),
                new Position(14, -24),
                new Position(17, -10),
                new Position(12, -6),
                new Position(16, 3),
                new Position(10, 12),
                new Position(-5, 17),
                new Position(-13, 11),
                new Position(-23, 10),
                new Position(-30, 1),
                new Position(-15, -5),
                new Position(-15, -14)};
        setCollisionArea(new CollisionArea(new Position(31, 28), boundary));
    }

    public Unit getDrawResource() {
        return this;
    }
}
