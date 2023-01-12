package com.example.dontopen.view.levels.lvl2;

import com.example.dontopen.DontOpenMain;
import com.example.dontopen.view.Initable;
import javafx.scene.layout.Pane;

public class MazePane extends Pane implements Initable {

    final private DontOpenMain application;

    public MazePane(DontOpenMain application){
        this.application = application;
    }

    public void init(){

    }

    public DontOpenMain getApplication() {
        return application;
    }
}
