package com.example.dontopen.control;

import com.example.dontopen.view.MainPane;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EnterPressedHandler implements EventHandler<KeyEvent> {

    final private MainPane mainPane;

    public EnterPressedHandler(MainPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            mainPane.getTitle().setText("How dare you... That's it.");
            mainPane.getButton().setVisible(false);

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Platform.runLater(() -> {
                    mainPane.getText().setVisible(true);
                    if(mainPane.getApplication().getLevel()==1) {
                        mainPane.getApplication().nextLevel();
                    }
                });

            }).start();

        }
    }
}
