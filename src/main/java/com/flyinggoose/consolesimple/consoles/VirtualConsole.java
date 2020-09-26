package com.flyinggoose.consolesimple.consoles;

import com.flyinggoose.consolesimple.utils.TextCharacter;
import com.flyinggoose.consolesimple.display.ConsoleCharacter;
import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class VirtualConsole implements Console {
    private boolean running = false;
    private boolean stopped = false;
    private ConsoleSize consoleSize = new ConsoleSize(10, 10);
    private List<List<ConsoleCharacter>> rows = new LinkedList<>();
    private ConsolePosition lastEdit = new ConsolePosition(0, 0);

    private void updateChars() {
        if (!running) return;

        List<List<ConsoleCharacter>> newChars = new LinkedList<>();
        for (int y = 0; y < consoleSize.getHeight(); y++) {
            List<ConsoleCharacter> row = new LinkedList<>();
            for (int x = 0; x < consoleSize.getWidth(); x++) {
                ConsoleCharacter cc;

                try {
                    cc = rows.get(y).get(x);
                } catch (IndexOutOfBoundsException e) {
                    cc = new ConsoleCharacter(new TextCharacter(' ', false, false), new ConsolePosition(x, y));
                }

                row.add(cc);
            }
            newChars.add(row);
        }

        rows = newChars;
    }

    @Override
    public void resetCharAt(ConsolePosition pos) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) setCharAt(pos, ' ');
    }

    @Override
    public void resetBackgroundAt(ConsolePosition pos) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setBackground(Color.BLACK);
    }

    @Override
    public void resetForegroundAt(ConsolePosition pos) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setForeground(Color.WHITE);
    }

    @Override
    public void setCharAt(ConsolePosition pos, char c) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) {
            this.lastEdit = pos;
            rows.get(pos.getY()).get(pos.getX()).setTextCharacter(new TextCharacter(c, false, false));
        }
    }

    @Override
    public void setBackgroundAt(ConsolePosition pos, Color c) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setBackground(c);
    }

    @Override
    public void setForegroundAt(ConsolePosition pos, Color c) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setForeground(c);
    }

    @Override
    public char getCharAt(ConsolePosition pos) {
        if (!running) return ' ';

        if (getConsoleSize().isValid(pos))
            return rows.get(pos.getY()).get(pos.getX()).getTextCharacter().getCharacter();
        return ' ';
    }

    @Override
    public Color getBackgroundAt(ConsolePosition pos) {
        if (!running) return null;

        if (getConsoleSize().isValid(pos)) return rows.get(pos.getY()).get(pos.getX()).getBackground();
        return Color.BLACK;
    }

    @Override
    public Color getForegroundAt(ConsolePosition pos) {
        if (!running) return null;

        if (getConsoleSize().isValid(pos)) return rows.get(pos.getY()).get(pos.getX()).getForeground();
        return Color.WHITE;
    }

    @Override
    public ConsolePosition getLastEdit() {
        if (!running) return null;

        return this.lastEdit;
    }

    @Override
    public ConsoleSize getConsoleSize() {
        if (!running) return null;

        return this.consoleSize;
    }

    @Override
    public void setConsoleSize(ConsoleSize size) {
        if (!running) return;

        this.consoleSize = size;
        updateChars();
    }

    @Override
    public void clearScreen() {
        if (!running) return;

        this.rows.clear();
        updateChars();
    }

    @Override
    public void start() {
        if (!stopped) this.running = true;
    }

    @Override
    public void stop() {
        clearScreen();
        this.running = false;
        this.stopped = true;
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
