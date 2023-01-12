package com.example.dontopen;

import com.example.dontopen.view.GamePaneWrapper;
import com.example.dontopen.view.MainPane;
import com.example.dontopen.view.levels.lvl2.MazePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

        switch (level) {
            case 1 -> {
                currentGamePane = new GamePaneWrapper(new MainPane(this, mainStage));
                currentGamePane.initPane();
                mainStage.setScene(new Scene(currentGamePane.getPane(), 800, 500));
            }
            case 2 -> {
                MazePane lvl2Pane = new MazePane(this);
                lvl2Pane.init();
                currentGamePane = new GamePaneWrapper(lvl2Pane);
                mainStage.setScene(new Scene(lvl2Pane, 1280, 720));
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
            fileError.setTitle("Unable to save!");
            fileError.setHeaderText("Unable to write to game_config!");
            fileError.setContentText("Game is unable to write the current game progress to game_config. Check file!");
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