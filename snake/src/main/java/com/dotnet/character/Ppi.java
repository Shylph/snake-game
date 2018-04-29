package com.dotnet.character;

import com.dotnet.imageSource.ImageSource;

public class Ppi extends Unit {

    public Ppi() {
        super(ImageSource.getPpiImg(), 60, 60);
    }

    public Unit getDrawResource() {
        return this;
    }
}
