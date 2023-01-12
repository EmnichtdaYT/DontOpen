package com.example.dontopen.model.levels.lvl2;

import javafx.scene.Node;

import java.util.ArrayList;

public class Maze {
    private RunnableMazeSchedule mazeSchedule;
    private ArrayList<MazeEvent> mazeEvents;
    private ArrayList<Node> mazeMap;

    public Maze(RunnableMazeSchedule mazeSchedule, ArrayList<MazeEvent> mazeEvents, ArrayList<Node> mazeMap){

    }

    public RunnableMazeSchedule getMazeSchedule() {
        return mazeSchedule;
    }

    public void setMazeSchedule(RunnableMazeSchedule mazeSchedule) throws IllegalArgumentException {
        if(mazeSchedule == null) throw new IllegalArgumentException("mazeSchedule can't be null!");
        this.mazeSchedule = mazeSchedule;
    }

    public ArrayList<MazeEvent> getMazeEvents() {
        return mazeEvents;
    }

    public void setMazeEvents(ArrayList<MazeEvent> mazeEvents) throws IllegalArgumentException {
        if(mazeEvents == null) throw new IllegalArgumentException("mazeEvent can't be null!");
        this.mazeEvents = mazeEvents;
    }

    public ArrayList<Node> getMazeMap() {
        return mazeMap;
    }

    public void setMazeMap(ArrayList<Node> mazeMap) throws IllegalArgumentException {
        if(mazeMap == null) throw new IllegalArgumentException("mazeMap can't be null!");
        this.mazeMap = mazeMap;
    }
}
