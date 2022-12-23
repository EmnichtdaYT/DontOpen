package com.example.dontopen.view;

import javafx.scene.layout.Pane;

public class GamePaneWrapper {

    private Pane p;

    public GamePaneWrapper(Pane pane){
        setPane(pane);
    }

    public void setPane(Pane p) {
        if(p instanceof Initable) {
            this.p = p;
        }else{
            throw new IllegalArgumentException("Pane needs to be of type initable!");
        }
    }

    public Pane getPane(){
        return p;
    }

    public Initable getInitablePane(){
        return (Initable) p;
    }

    public void initPane(){
        ((Initable) p).init();
    }
}
