package com.dotnet.character;

import javax.swing.ImageIcon;

public class Ppi extends Unit {

    public Ppi() {
        super("ppi",new ImageIcon("res/ppi_1.png").getImage(), 60, 60);
    }

    public Unit getDrawResource() {
        return this;
    }
}
