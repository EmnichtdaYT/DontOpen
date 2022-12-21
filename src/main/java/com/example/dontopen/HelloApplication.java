package com.example.dontopen;

import com.example.dontopen.view.MainPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        MainPane mainPane = new MainPane(stage);
        mainPane.init();

        stage.setTitle("Don't open");
        stage.setScene(new Scene(mainPane, 800, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}