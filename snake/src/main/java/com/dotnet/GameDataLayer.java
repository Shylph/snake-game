package com.dotnet;

public class GameDataLayer {
    private final DrawResourceManager drawResourceManager;
    private final ScreenConfig screenConfig;
    private boolean inGame = true;
    private final int RAND_POS = 29;
    private final int DELAY = 50;

    public GameDataLayer(DrawResourceManager drawResourceManager,ScreenConfig screenConfig){
        this.drawResourceManager = drawResourceManager;
        this.screenConfig = screenConfig;
    }

    public boolean isInGame() {
        return inGame;
    }
    public void falseInGame(){
        inGame = false;
    }
    public void trueInGame(){
        inGame = true;
    }

    public int getDELAY() {
        return DELAY;
    }

    public int getRAND_POS() {
        return RAND_POS;
    }

     /*public void checkCollision() {
        boolean tempInGame = true;
        for (int z = snake.getDots(); z > 0; z--) {
            if ((z > 4) && (snake.getX()[0] == snake.getX()[z]) && (snake.getY()[0] == snake.getY()[z])) {
                tempInGame = false;
            }
        }

        if (snake.getY()[0] >= screenConfig.getHeight()) {
            tempInGame = false;
        }

        if (snake.getY()[0] < 0) {
            tempInGame = false;
        }

        if (snake.getX()[0] >= screenConfig.getWidth()) {
            tempInGame = false;
        }

        if (snake.getX()[0] < 0) {
            tempInGame = false;
        }
        if(!tempInGame)
            falseInGame();
    }*/
}
