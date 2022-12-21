package com.example.dontopen.control;

import javafx.application.Platform;
import javafx.scene.robot.Robot;

public class SlowlyMoveMousePointer implements Runnable{

    private Robot robot = new Robot();
    private double xDelta;
    private double yDelta;
    private double currentX;
    private double currentY;

    public SlowlyMoveMousePointer(Robot robot, double xDelta, double yDelta, double currentX, double currentY){
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
                int xDeltaThisStep = 0;
                int yDeltaThisStep = 0;

                if(xDelta>0){
                    xDeltaThisStep = -1;
                    xDelta--;
                    currentX--;
                }else if(xDelta<0){
                    xDeltaThisStep = 1;
                    xDelta++;
                    currentX++;
                }

                if(yDelta>0){
                    yDeltaThisStep = -1;
                    yDelta--;
                    currentY--;
                }else if(yDelta<0){
                    yDeltaThisStep = 1;
                    yDelta++;
                    currentY++;
                }

                final int finalXDeltaThisStep = xDeltaThisStep;
                final int finalYDeltaThisStep = yDeltaThisStep;
                Platform.runLater(() -> {robot.mouseMove(currentX + finalXDeltaThisStep, currentY + finalYDeltaThisStep);});

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Robot getRobot() {
        return robot;
    }

    public double getXDelta() {
        return xDelta;
    }

    public double getYDelta() {
        return yDelta;
    }

    public double getCurrentX() {
        return currentX;
    }

    public double getCurrentY() {
        return currentY;
    }
}
