package com.dotnet;

public class ScoreBoard {
    private final String name;
    private int score;
    private final String targetWord;
    private String word;

    ScoreBoard(String name) {
        this.name = name;
        score = 0;
        targetWord = "APPLE";
        word="";
    }


    public void addScore(int score) {
        this.score += score;
    }


    public int getScore() {
        return score;
    }

    public void addAlphabet(String name) {
        word += name;
        char[] alphabets = word.toCharArray();
        char[] target = targetWord.toCharArray();

        for (int i = 0; i < alphabets.length; i++) {
            char temp = alphabets[i];
            if(alphabets.length == target.length && targetWord.equals(word)){
                addScore(10000);
                word="";
                break;
            }
            if(temp != target[i]){
                word="";
                break;
            }
        }
    }
}
