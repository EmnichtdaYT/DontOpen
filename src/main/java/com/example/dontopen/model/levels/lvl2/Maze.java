package com.example.dontopen.model.levels.lvl2;

import javafx.scene.Node;

import java.util.ArrayList;

public class Maze {
    private RunnableMazeSchedule mazeSchedule;
    private ArrayList<MazeEvent> mazeEvents;
    private ArrayList<Node> mazeMap;

    public Maze(ArrayList<Node> mazeMap, RunnableMazeSchedule mazeSchedule, ArrayList<MazeEvent> mazeEvents) {
        setMazeMap(mazeMap);
        setMazeSchedule(mazeSchedule);
        setMazeEvents(mazeEvents);
    }

    public RunnableMazeSchedule getMazeSchedule() {
        return mazeSchedule;
    }

    private void setMazeSchedule(RunnableMazeSchedule mazeSchedule) throws IllegalArgumentException {
        if(mazeSchedule == null) throw new IllegalArgumentException("mazeSchedule can't be null!");
        this.mazeSchedule = mazeSchedule;
    }

    public ArrayList<Node> getMazeMap() {
        return mazeMap;
    }

    private void setMazeMap(ArrayList<Node> mazeMap) throws IllegalArgumentException {
        if(mazeMap == null) throw new IllegalArgumentException("mazeMap can't be null!");
        this.mazeMap = mazeMap;
    }

    public ArrayList<MazeEvent> getMazeEvents() {
        return mazeEvents;
    }

    private void setMazeEvents(ArrayList<MazeEvent> mazeEvents) throws IllegalArgumentException {
        if(mazeEvents == null) mazeEvents = new ArrayList<>();
        this.mazeEvents = mazeEvents;
    }
}
