package com.example.dontopen.model.levels.lvl2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunnableMazeSchedule implements Runnable{

    private ArrayList<MazeTask> mazeTasks;

    public RunnableMazeSchedule(ArrayList<MazeTask> mazeTasks){
        setMazeTasks(mazeTasks);
    }

    public List<MazeTask> getMazeTasks() {
        return Collections.unmodifiableList(mazeTasks);
    }

    private void setMazeTasks(ArrayList<MazeTask> mazeTasks) {
        if(mazeTasks==null){
            throw new IllegalArgumentException("The tasks can't be null!");
        }
        this.mazeTasks = mazeTasks;
    }

    @Override
    public void run() {
        for(MazeTask task : mazeTasks){
            doTask(task);
        }
    }

    private void doTask(MazeTask task){
        try {
            Thread.sleep(task.getDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.getRunnableTask().run();
    }
}
