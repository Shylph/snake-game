package com.dotnet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameKeyAdapter extends KeyAdapter {
    private List<KeyListener> keyListeners;

    GameKeyAdapter(){
        keyListeners = new ArrayList<>();
    }

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(KeyListener listener : keyListeners){
            listener.keyPressed(e);
        }
    }
}
