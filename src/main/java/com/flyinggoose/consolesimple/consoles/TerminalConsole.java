package com.flyinggoose.consolesimple.consoles;

import com.flyinggoose.consolesimple.display.ConsoleCharacter;
import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class TerminalConsole implements Runnable, Console {
    private Thread thread;
    List<List<ConsoleCharacter>> rows = new LinkedList<>();
    private boolean running = false;
    private ConsoleSize consoleSize = new ConsoleSize(10, 10);
    private ConsolePosition lastEdit = new ConsolePosition(0, 0);

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (List<ConsoleCharacter> row : rows) {

            }
        }
    }

    @Override
    public void resetCharAt(ConsolePosition pos) {

    }

    @Override
    public void resetBackgroundAt(ConsolePosition pos) {

    }

    @Override
    public void resetForegroundAt(ConsolePosition pos) {

    }

    @Override
    public void setCharAt(ConsolePosition pos, char c) {

    }

    @Override
    public void setBackgroundAt(ConsolePosition pos, Color c) {

    }

    @Override
    public void setForegroundAt(ConsolePosition pos, Color c) {

    }

    @Override
    public char getCharAt(ConsolePosition pos) {
        return 0;
    }

    @Override
    public Color getBackgroundAt(ConsolePosition pos) {
        return null;
    }

    @Override
    public Color getForegroundAt(ConsolePosition pos) {
        return null;
    }

    @Override
    public ConsolePosition getLastEdit() {
        return null;
    }

    @Override
    public ConsoleSize getConsoleSize() {
        return null;
    }

    @Override
    public void setConsoleSize(ConsoleSize size) {

    }

    @Override
    public void clearScreen() {

    }

    @Override
    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "Console");
        this.thread.start();
    }

    @Override
    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String getNextLine() {
        return null;
    }

    @Override
    public String getLastLine() {
        return null;
    }

    @Override
    public String getAllLines() {
        return null;
    }

    @Override
    public boolean isPressing(char key) {
        return false;
    }

    @Override
    public boolean isPressing(int key) {
        return false;
    }
}
