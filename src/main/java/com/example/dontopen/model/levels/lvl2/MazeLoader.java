package com.example.dontopen.model.levels.lvl2;

import com.example.dontopen.logging.Logable;
import com.example.dontopen.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MazeLoader implements Logable {

    private Maze parsedMaze;

    private File mapFile;
    private File scheduleFile;
    private File eventsFile;

    private BufferedReader mapReader;
    private BufferedReader scheduleReader;
    private BufferedReader eventsReader;

    public MazeLoader(File mapFile, File scheduleFile) throws FileNotFoundException {
        setMapFile(mapFile);
        setScheduleFile(scheduleFile);
    }

    public MazeLoader(File levelDirectory) throws FileNotFoundException {
        File[] levelDirectoryFiles = levelDirectory.listFiles();

        if (levelDirectoryFiles == null || levelDirectoryFiles.length < 2) {
            throw new FileNotFoundException("levelDirectory must contain at least 2 files, containing at least one map and one schedule file");
        }

        Logger.getInstance().logInfo("Attempting to find maze files in directory '" + levelDirectory.getAbsolutePath() + "'.", this);
        Logger.getInstance().logInfo("Directory contains " + levelDirectoryFiles.length + " files.", this);

        for (File f : levelDirectoryFiles) {
            if (!f.isFile()) {
                continue;
            }
            if (f.getName().equals("map")) {
                Logger.getInstance().logInfo("Found map file, attempting to set it.", this);
                setMapFile(f);
            } else if (f.getName().equals("schedule")) {
                Logger.getInstance().logInfo("Found schedule file, attempting to set it.", this);
                setScheduleFile(f);
            } else if (f.getName().equals("events")) {
                Logger.getInstance().logInfo("Found events file, attempting to set it.", this);
                setEventsFile(f);
            }
        }

        if (mapFile == null || scheduleFile == null) {
            throw new FileNotFoundException("levelDirectory must contain at least one map and one schedule file");
        }

        Logger.getInstance().logInfo("Done! Summary: " + this.toString(), this);
    }

    private void initReaders() throws FileNotFoundException {
        mapReader = new BufferedReader(new FileReader(mapFile));
        scheduleReader = new BufferedReader(new FileReader(scheduleFile));
        eventsReader = new BufferedReader(new FileReader(eventsFile));
    }

    public void loadMaze() throws FileNotFoundException {
        initReaders();
        //TODO Implement
    }

    public Maze getMaze() {
        return parsedMaze;
    }

    public File getMapFile() {
        return mapFile;
    }

    private void setMapFile(File mapFile) throws FileNotFoundException {
        if (mapFile.exists() && mapFile.isFile() && mapFile.canRead()) {
            this.mapFile = mapFile;
        } else {
            throw new FileNotFoundException("Can't read map file! Check if it exists and if it is readable!");
        }
    }

    public File getScheduleFile() {
        return scheduleFile;
    }

    private void setScheduleFile(File scheduleFile) throws FileNotFoundException {
        if (scheduleFile.exists() && scheduleFile.isFile() && scheduleFile.canRead()) {
            this.scheduleFile = scheduleFile;
        } else {
            throw new FileNotFoundException("Can't read schedule file! Check if it exists and if it is readable!");
        }
    }

    public File getEventsFile() {
        return eventsFile;
    }

    public void setEventsFile(File eventsFile) throws FileNotFoundException {
        if (eventsFile.exists() && eventsFile.isFile() && eventsFile.canRead()) {
            this.eventsFile = eventsFile;
        } else {
            throw new FileNotFoundException("Can't read events file! Check if it exists and if it is readable!");
        }
    }

    private BufferedReader getMapReader() {
        return mapReader;
    }

    private BufferedReader getScheduleReader() {
        return scheduleReader;
    }

    private BufferedReader getEventsReader() {
        return eventsReader;
    }

    @Override
    public String toString() {
        return "MazeLoader{" +
                "parsedMaze=" + parsedMaze +
                ", mapFile='" + mapFile + "'" +
                ", scheduleFile='" + scheduleFile + "'" +
                ", eventsFile='" + eventsFile + "'" +
                ", mapReader=" + mapReader + "'" +
                ", scheduleReader=" + scheduleReader + "'" +
                ", eventsReader=" + eventsReader + "'" +
                '}';
    }

    @Override
    public String getLogPrefix() {
        return "[MazeLoader: " + hashCode() + "]";
    }
}
