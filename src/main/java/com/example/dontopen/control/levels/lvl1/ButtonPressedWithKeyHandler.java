package com.example.dontopen.control.levels.lvl1;

import com.example.dontopen.view.MainPane;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ButtonPressedWithKeyHandler implements EventHandler<KeyEvent> {

    final private MainPane mainPane;

    public ButtonPressedWithKeyHandler(MainPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
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
                    mainPane.getApplication().getHostServices().showDocument("https://ia804609.us.archive.org/4/items/rick-roll/Rick%20Roll.ia.mp4");
                });

                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Platform.runLater(() -> {
                    mainPane.requestFocus();
                    mainPane.getTitle().setStyle("");
                    mainPane.getTitle().setText("So, you wanna play games?");
                    mainPane.getText().setStyle("");
                    mainPane.getText().setText("Ok, let's go!");
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Platform.runLater(() -> mainPane.getApplication().nextLevel());

            }).start();

        }
    }
}
