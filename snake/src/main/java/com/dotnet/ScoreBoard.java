package com.dotnet;

public class ScoreBoard {
    private int score;
    private final String targetWord;
    private String word;

    ScoreBoard() {
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
        System.out.println(word);
        System.out.println(word.length());
        char[] alphabets = word.toCharArray();
        char[] target = targetWord.toCharArray();

        System.out.println(targetWord);
        System.out.println(targetWord.length());

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
