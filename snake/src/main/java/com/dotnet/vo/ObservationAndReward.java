package com.dotnet.vo;

import com.dotnet.character.Direction;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ObservationAndReward {
    private int reward;
    private int width;
    private int height;
    private Integer[][]  rawData;
    private int action;
    private boolean gameOverFlag;

/*    public void setRawData(int[][] data){
  *//*      rawData=new ArrayList<>();
        for(int[] y : data){
            for(int x : y){
                rawData.add(x);
            }
        }*//*
    }*/

    public void setAction(Direction direction){
        switch (direction){
            case LEFT:
                action=0;
                break;
            case RIGHT:
                action=1;
                break;
            case UP:
                action=2;
                break;
            case DOWN:
                action=3;
                break;
        }
    }

}
