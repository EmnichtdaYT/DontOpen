package com.example.dontopen.control;

import com.example.dontopen.view.MainPane;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.robot.Robot;

import java.util.Random;

public class ClickPreventHandler implements EventHandler<MouseEvent>{

    final private MainPane mainPane;
    final private Robot robot = new Robot();
    private boolean locked = false;
    private int userSufferCount;

    public ClickPreventHandler(MainPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public void handle(MouseEvent event) {

        if(locked) return;

        locked = true;
        userSufferCount++;

        if(userSufferCount==3){
            mainPane.getButton().setText("DON'T. TOUCH.");
        }else if(userSufferCount==10){
            mainPane.getTitle().setText("Can you even read? DON'T. TOUCH. THE. BUTTON.");
        }else if(userSufferCount==15){
            mainPane.getTitle().setStyle("-fx-text-fill: red;");
        }else if(userSufferCount==20){
            mainPane.getTitle().setText("YOUR STUPID MOUSE WON'T HELP YOU! STOP IT!");
        }

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

            new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                unlock();
            }).start();

        }else{

            int xDelta = 0;
            int yDelta = 0;

            if(r.nextBoolean()){
                xDelta = r.nextInt(30)+20;
            }else{
                yDelta = r.nextInt(30)+20;
            }

            if(r.nextBoolean()){
                xDelta*=-1;
            }
            if(r.nextBoolean()){
                yDelta*=-1;
            }
            new Thread(new SlowlyMoveMousePointer(this, robot, xDelta, yDelta, robot.getMouseX(), robot.getMouseY())).start();
        }
    }

    protected void unlock(){
        locked = false;
    }

    public MainPane getMainPane(){
        return mainPane;
    }

    public int getUserSufferCount() {
        return userSufferCount;
    }
}
