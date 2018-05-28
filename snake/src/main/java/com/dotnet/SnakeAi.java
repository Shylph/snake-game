package com.dotnet;

import com.dotnet.character.snake.Snake;

public class SnakeAi {
    private final Snake snake;
    private int cnt;

    public SnakeAi(Snake snake){
        this.snake = snake;
        cnt=0;
    }
    public void move(){
        if(cnt>60){
            snake.left();
            cnt = 0;
        }else if(cnt>50){
            snake.down();
        }else if(cnt>30){
            snake.right();
        }else if(cnt>20){
            snake.up();
        }

        cnt++;

        snake.move();
    }

}
