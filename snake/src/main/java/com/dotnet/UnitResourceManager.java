package com.dotnet;

import com.dotnet.character.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnitResourceManager {
    private List<Unit> drawResources;

    public UnitResourceManager() {
        drawResources = new ArrayList<>();
    }

    public Unit[] getUnitResources() {
        Unit[] temp = new Unit[drawResources.size()];
        return drawResources.toArray(temp);
    }

    public List<Unit> getUnitResources(String name) {
        List<Unit> result = new ArrayList<>();
        for (Unit unit : drawResources) {
            if (unit.isName(name)) {
                result.add(unit);
            }
        }
        return result;
    }

    public Unit getUnitResource(String name) {
        for (Unit unit : drawResources) {
            if (unit.isName(name)) {
                return unit;
            }
        }
        return null;
    }


    public void addUnit(Unit[] bodyResource) {
        drawResources.addAll(Arrays.asList(bodyResource));
    }

    public void addUnit(Unit resource) {
        drawResources.add(resource);
    }

    public void addUnit(List<Unit> drawResource) {
        drawResources.addAll(drawResource);
    }

    public void clear() {
        drawResources.clear();
    }

    public void removeUnit(String name) {
        for (Unit unit : drawResources) {
            if (unit.isName(name)) {
                drawResources.remove(unit);
                break;
            }
        }
    }
}
