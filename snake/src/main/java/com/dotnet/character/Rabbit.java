package com.dotnet.character;

import javax.swing.ImageIcon;

public class Rabbit extends Unit {

    public Rabbit( ) {
        super("rabbit",new ImageIcon("res/Rabbit_1.png").getImage(), 60, 60);
    }

    public Unit getDrawResource() {
        return this;
    }
}
