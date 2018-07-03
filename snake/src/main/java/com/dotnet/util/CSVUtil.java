package com.dotnet.util;

import com.dotnet.vo.ObservationAndReward;
import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class CSVUtil {
    private final String fileName;
    private final String filePath;
    private boolean firstRunFlag = false;


    private boolean appendCSV(String row) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filePath + "/" + fileName + ".csv", true));
            bufferedWriter.write(row);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void createHeader(int size) {
        File file =new File(filePath + "/" + fileName + ".csv");
        if(file.exists()){
            file.delete();
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(i).append(",");
        }
        s.append("action,");
        s.append("reward,");
        s.append("gameOverFlag");
        appendCSV(s.toString());
    }

    public boolean appendCSV(ObservationAndReward observationAndReward) {
        List<Integer> rawData = new ArrayList<>();
        Integer[][] ints = observationAndReward.getRawData();
        for (Integer[] anInts : ints) {
            rawData.addAll(Arrays.asList(anInts));
        }
        if (!firstRunFlag) {
            createHeader(rawData.size());
            firstRunFlag = true;
        }
        StringBuilder s = new StringBuilder();
        for (int d : rawData) {
            s.append(d).append(",");
        }
        s.append(observationAndReward.getAction()).append(",");
        s.append(observationAndReward.getReward()).append(",");
        s.append(observationAndReward.isGameOverFlag());

        return appendCSV(s.toString());
    }


}
