package com.example.dontopen.logging;

public enum LogLevel {

    INFO, WARNING, ERROR;

    private LogLevel(){ }

    public String getPrefix(){
        return this.name();
    }

}
