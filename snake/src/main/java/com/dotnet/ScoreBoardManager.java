package com.dotnet;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoardManager {
    private Map<String, ScoreBoard> map;

    public ScoreBoardManager() {
        map = new HashMap<>();
    }

    public void register(String name) {
        map.put(name, new ScoreBoard(name));
    }

    public ScoreBoard getBoardAnyOne() {
        if(!map.isEmpty()){
            Object key  = map.keySet().toArray()[0];
            return map.get(key);
        }
        return null;
    }

    public ScoreBoard getBoard(String name) {
        return map.get(name);
    }
}
