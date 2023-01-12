package com.example.dontopen;

import com.example.dontopen.view.GamePaneWrapper;
import com.example.dontopen.view.MainPane;
import com.example.dontopen.view.levels.lvl2.MazePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DontOpenMain extends Application {

    private int level = 0;
    private Stage mainStage;
    private GamePaneWrapper currentGamePane;

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;
        stage.setTitle("Don't open");
        nextLevel();
        stage.show();
    }


    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        level++;
        saveCurrentLevel();
        Pane currentPane = null;
        if(currentGamePane!=null) currentPane = currentGamePane.getPane();

        switch (level) {
            case 1 -> {
                currentGamePane = new GamePaneWrapper(new MainPane(this, mainStage));
                currentGamePane.initPane();
                currentPane = currentGamePane.getPane();
                mainStage.setScene(new Scene(currentPane, 800, 500));
            }
            case 2 -> {
                final MainPane level2Pane = (MainPane) currentPane;
                getHostServices().showDocument("https://ia804609.us.archive.org/4/items/rick-roll/Rick%20Roll.ia.mp4");
                new Thread(() -> {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Platform.runLater(() -> {
                        mainStage.requestFocus();
                        level2Pane.getTitle().setStyle("");
                        level2Pane.getTitle().setText("So, you wanna play games?");
                        level2Pane.getText().setStyle("");
                        level2Pane.getText().setText("Ok, let's go!");
                    });

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Platform.runLater(this::nextLevel);

                }).start();
            }
            case 3 ->  {
                MazePane lvl3Pane = new MazePane(this);
                lvl3Pane.init();
                currentGamePane = new GamePaneWrapper(lvl3Pane);
                currentPane = lvl3Pane;
                mainStage.setScene(new Scene(currentPane, 1280, 720));
                mainStage.setFullScreen(true);
            }
        }

    }

    private void saveCurrentLevel() {
        File f = new File("game_config");

        try {
            f.delete();
            f.createNewFile();

            FileWriter fWriter = new FileWriter(f);
            fWriter.write(Character.forDigit(level, 10));
            fWriter.flush();
            fWriter.close();

        } catch (IOException e) {
            e.printStackTrace();

            Alert fileError = new Alert(Alert.AlertType.ERROR);
            fileError.setTitle("Can't write to file!");
            fileError.setHeaderText("Unable to write to game_config!");
            fileError.setContentText("Game is unable to write to game_config. Check file!");
            fileError.showAndWait().ifPresent(result -> mainStage.close());
        }

    }

    public Stage getMainStage() {
        return mainStage;
    }

    public GamePaneWrapper getCurrentGamePane() {
        return currentGamePane;
    }

    public static void main(String[] args) {
        launch();
    }
}