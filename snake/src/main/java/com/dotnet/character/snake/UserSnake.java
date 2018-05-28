package com.dotnet.character.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserSnake extends Snake implements KeyListener {
    public UserSnake(String headPath, String bodyPath) {
        super(headPath,bodyPath);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT)) {
            left();
        } else if ((key == KeyEvent.VK_RIGHT)) {
            right();
        } else if ((key == KeyEvent.VK_UP)) {
            up();
        } else if ((key == KeyEvent.VK_DOWN)) {
            down();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
