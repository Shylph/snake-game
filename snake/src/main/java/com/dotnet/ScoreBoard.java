package com.dotnet;

public class ScoreBoard {
    private int score;

    ScoreBoard() {
        score = 0;
    }


    public void addScore(int score) {
        this.score += score;
    }


    public int getScore() {
        return score;
    }
}
