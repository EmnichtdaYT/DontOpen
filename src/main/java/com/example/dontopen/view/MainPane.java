package com.example.dontopen.view;

import com.example.dontopen.Utils;
import com.example.dontopen.control.ClickPreventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainPane extends GridPane {

    private Stage stage;

    public MainPane(Stage stage){
        this.stage = stage;
    }

    private Button theThingTheUserShouldntClick;
    private Label title;

    public void init(){
        this.setAlignment(Pos.CENTER);

        title = new Label("Hello, " + Utils.capitalize(System.getProperty("user.name")) + ". Don't click the button.");
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(30));

        theThingTheUserShouldntClick = new Button("Button");
        theThingTheUserShouldntClick.setAlignment(Pos.CENTER);
        theThingTheUserShouldntClick.setTextAlignment(TextAlignment.CENTER);
        theThingTheUserShouldntClick.setOnMouseMoved(new ClickPreventHandler(this));

        this.add(title, 1, 1);
        this.add(theThingTheUserShouldntClick, 1, 2);

        this.getChildren().forEach(child -> {
            GridPane.setHalignment(child, HPos.CENTER);
            GridPane.setValignment(child, VPos.CENTER);
            GridPane.setMargin(child, new Insets(10));
        });
    }

    public Label getTitle(){
        return title;
    }

    public Button getButton(){
        return theThingTheUserShouldntClick;
    }

    public Stage getStage(){
        return stage;
    }
}
