package com.example.dontopen.view.levels.lvl2;

import com.example.dontopen.HelloApplication;
import com.example.dontopen.view.Initable;
import javafx.scene.layout.Pane;

public class MazePane extends Pane implements Initable {

    final private HelloApplication application;

    public MazePane(HelloApplication application){
        this.application = application;
    }

    public void init(){

    }

    public HelloApplication getApplication() {
        return application;
    }
}
