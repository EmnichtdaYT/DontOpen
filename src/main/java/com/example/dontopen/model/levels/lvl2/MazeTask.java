package com.example.dontopen.model.levels.lvl2;

public abstract class MazeTask {
    private int delay;

    public MazeTask(int delay){
        setDelay(delay);
    }

    public int getDelay() {
        return delay;
    }

    private void setDelay(int delay) throws IllegalArgumentException {
        if(delay < 0){
            throw new IllegalArgumentException("Delay can't be negative. Time-travel has not been implemented yet.");
        }
        this.delay = delay;
    }

    public abstract Runnable getRunnableTask();
}
