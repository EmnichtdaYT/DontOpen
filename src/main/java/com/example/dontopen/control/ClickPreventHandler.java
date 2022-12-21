package com.example.dontopen.control;

import com.example.dontopen.view.MainPane;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.robot.Robot;

import java.util.Random;

public class ClickPreventHandler implements EventHandler<MouseEvent>{

    private MainPane mainPane;
    private Robot robot = new Robot();

    public ClickPreventHandler(MainPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public void handle(MouseEvent event) {

        Button button = (Button) event.getSource();

        Random r = new Random();

        if(r.nextBoolean()) {
            if(GridPane.getHalignment(button) == HPos.LEFT) {
                if(r.nextBoolean()){
                    GridPane.setHalignment(button, HPos.CENTER);
                }else{
                    GridPane.setHalignment(button, HPos.RIGHT);
                }
            }else if(GridPane.getHalignment(button) == HPos.RIGHT){
                if(r.nextBoolean()){
                    GridPane.setHalignment(button, HPos.LEFT);
                }else{
                    GridPane.setHalignment(button, HPos.CENTER);
                }
            }else{
                if(r.nextBoolean()){
                    GridPane.setHalignment(button, HPos.LEFT);
                }else{
                    GridPane.setHalignment(button, HPos.RIGHT);
                }
            }
        }else{
            int xDelta = r.nextInt(30)+60;
            int yDelta = r.nextInt(20)+50;
            if(r.nextBoolean()){
                xDelta*=-1;
            }
            if(r.nextBoolean()){
                yDelta*=-1;
            }
            new Thread(new SlowlyMoveMousePointer(robot, r.nextInt(), -50, robot.getMouseX(), robot.getMouseY())).start();
        }
    }

    public MainPane getMainPane(){
        return mainPane;
    }
}
