package com.example.dontopen.logging;

import java.time.LocalDateTime;

public class Logger {

    private static Logger instance;

    private Logger(){}

    public static Logger getInstance(){
        if(instance == null) instance = new Logger();
        return instance;
    }

    /***
     * Logs a warning
     * @param message message
     */
    public void logWarning(String message) {
        log(message, LogLevel.WARNING);
    }

    /***
     * Logs an info
     * @param message message
     */
    public void logInfo(String message) {
        log(message, LogLevel.INFO);
    }

    /***
     * Logs an error
     * @param message message
     */
    public void logError(String message) {
        log(message, LogLevel.ERROR);
    }

    /***
     * Logs a message with a given level
     * @param message message
     * @param level level
     */
    public void log(String message, LogLevel level) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("[LOGGER - " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " - " + level.getPrefix() + "] " + message);
    }

    /***
     * Logs a message with a given level
     * @param message message
     * @param instance logable instance which this log message corresponds to
     * @param level level
     */
    public void log(String message, Logable instance, LogLevel level){
        log(instance.getLogPrefix() + " " + message, level);
    }

    /***
     * Logs a warning
     * @param message message
     * @param instance logable instance which this log message corresponds to
     */
    public void logWarning(String message, Logable instance) {
        log(message, instance, LogLevel.WARNING);
    }

    /***
     * Logs an info
     * @param message message
     * @param instance logable instance which this log message corresponds to
     */
    public void logInfo(String message, Logable instance) {
        log(message, instance, LogLevel.INFO);
    }

    /***
     * Logs an error
     * @param message message
     * @param instance logable instance which this log message corresponds to
     */
    public void logError(String message, Logable instance) {
        log(message, instance, LogLevel.ERROR);
    }

}
