package com.example.dontopen;

import com.example.dontopen.view.MainPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {

    private int level = 0;
    private Stage mainStage;
    private MainPane mainPane;

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
                mainPane = new MainPane(this, mainStage);
                mainPane.init();
                mainStage.setScene(new Scene(mainPane, 800, 500));
            }
            case 2 -> {
                getHostServices().showDocument("https://ia804609.us.archive.org/4/items/rick-roll/Rick%20Roll.ia.mp4");
                new Thread(() -> {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Platform.runLater(() -> {
                        mainStage.requestFocus();
                        mainPane.getText().setText("Do you want some more of that? Sure thing!");
                        mainPane.getText().setStyle("-fx-text-fill: red;");
                    });

                    for (int i = 0; i < 5; i++) {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        getHostServices().showDocument("https://ia804609.us.archive.org/4/items/rick-roll/Rick%20Roll.ia.mp4");
                    }

                }).start();
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

    public static void main(String[] args) {
        launch();
    }
}