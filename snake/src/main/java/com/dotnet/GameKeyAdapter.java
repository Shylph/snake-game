package com.dotnet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyAdapter extends KeyAdapter {
    private KeyListener keyListener;

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyListener.keyPressed(e);
    }
}
