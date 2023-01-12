package com.example.dontopen.control.levels.lvl1;

import javafx.application.Platform;
import javafx.scene.robot.Robot;

public class SlowlyMoveMousePointer implements Runnable{

    final private Robot robot;
    private int xDelta;
    private int yDelta;
    private double currentX;
    private double currentY;
    final private ClickPreventHandler caller;

    public SlowlyMoveMousePointer(ClickPreventHandler caller, Robot robot, int xDelta, int yDelta, double currentX, double currentY){
        this.caller = caller;
        this.robot = robot;
        this.xDelta = xDelta;
        this.yDelta = yDelta;
        this.currentX = currentX;
        this.currentY = currentY;
    }

    private boolean isReached(){
        return xDelta==0&&yDelta==0;
    }

    @Override
    public void run() {
        try {
            while(!isReached()){

                int xPositionThisStep = (int) Math.floor(currentX);
                int yPositionThisStep = (int) Math.floor(currentY);

                if(xDelta>0){
                    xDelta--;
                    xPositionThisStep--;
                }else if(xDelta<0){
                    xDelta++;
                    xPositionThisStep++;
                }

                if(yDelta>0){
                    yDelta--;
                    yPositionThisStep--;
                }else if(yDelta<0){
                    yDelta++;
                    yPositionThisStep++;
                }

                currentX = xPositionThisStep;
                currentY = yPositionThisStep;

                final int finalXPositionThisStep = xPositionThisStep;
                final int finalYPositionThisStep = yPositionThisStep;
                Platform.runLater(() -> robot.mouseMove(finalXPositionThisStep, finalYPositionThisStep));

                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        caller.unlock();
    }

    public Robot getRobot() {
        return robot;
    }

    public int getXDelta() {
        return xDelta;
    }

    public int getYDelta() {
        return yDelta;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }
}
