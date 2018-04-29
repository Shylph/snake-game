package com.dotnet.character;

import com.dotnet.imageSource.ImageSource;

public class Rabbit extends Unit {

    public Rabbit( ) {
        super(ImageSource.getRabbitImg(), 60, 60);
    }

    public Unit getDrawResource() {
        return this;
    }
}
