package com.flyinggoose.consolesimple.consoles;

import com.flyinggoose.consolesimple.display.ConsoleGraphics;
import com.flyinggoose.consolesimple.utils.CharColor;
import com.flyinggoose.consolesimple.utils.TextCharacter;
import com.flyinggoose.consolesimple.display.ConsoleCell;
import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;

import java.util.LinkedList;
import java.util.List;

public class VirtualConsole implements Console {
    protected boolean running = false;
    protected boolean stopped = false;
    protected ConsoleSize consoleSize = new ConsoleSize(10, 10);
    protected List<List<ConsoleCell>> rows = new LinkedList<>();
    protected ConsolePosition lastEdit = new ConsolePosition(0, 0);
    private final ConsoleGraphics graphics;

    public VirtualConsole() {
        this.graphics = new ConsoleGraphics(this);
    }

    protected void updateChars() {
        if (!running) return;

        List<List<ConsoleCell>> newChars = new LinkedList<>();
        for (int y = 0; y < consoleSize.getHeight(); y++) {
            List<ConsoleCell> row = new LinkedList<>();
            for (int x = 0; x < consoleSize.getWidth(); x++) {
                ConsoleCell cc;

                try {
                    cc = rows.get(y).get(x);
                } catch (IndexOutOfBoundsException e) {
                    cc = new ConsoleCell(new TextCharacter(' '), new ConsolePosition(x, y));
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

        if (getConsoleSize().isValid(pos)) {
            setCharAt(pos, new TextCharacter(' ', false, false));
            updated();
        }
    }

    @Override
    public void resetBackgroundAt(ConsolePosition pos) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) {
            rows.get(pos.getY()).get(pos.getX()).setBackground(CharColor.ANSI.BLACK);
            updated();
        }
    }

    @Override
    public void resetForegroundAt(ConsolePosition pos) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) {
            rows.get(pos.getY()).get(pos.getX()).setForeground(CharColor.ANSI.WHITE);
            updated();
        }
    }

    @Override
    public void setCharAt(ConsolePosition pos, TextCharacter c) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) {
            this.lastEdit = pos;
            rows.get(pos.getY()).get(pos.getX()).setTextCharacter(c);
            updated();
        }
    }

    @Override
    public void setBackgroundAt(ConsolePosition pos, CharColor c) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) {
            rows.get(pos.getY()).get(pos.getX()).setBackground(c);
            updated();
        }
    }

    @Override
    public void setForegroundAt(ConsolePosition pos, CharColor c) {
        if (!running) return;

        if (getConsoleSize().isValid(pos)) {
            rows.get(pos.getY()).get(pos.getX()).setForeground(c);
            updated();
        }
    }

    @Override
    public TextCharacter getCharAt(ConsolePosition pos) {
        if (!running) return new TextCharacter(' ', false, false);

        if (getConsoleSize().isValid(pos))
            return rows.get(pos.getY()).get(pos.getX()).getTextCharacter();
        return new TextCharacter(' ', false, false);
    }

    @Override
    public CharColor getBackgroundAt(ConsolePosition pos) {
        if (!running) return null;

        if (getConsoleSize().isValid(pos)) return rows.get(pos.getY()).get(pos.getX()).getBackground();
        return CharColor.ANSI.BLACK;
    }

    @Override
    public CharColor getForegroundAt(ConsolePosition pos) {
        if (!running) return null;

        if (getConsoleSize().isValid(pos)) return rows.get(pos.getY()).get(pos.getX()).getForeground();
        return CharColor.ANSI.WHITE;
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
        updated();
    }

    @Override
    public void clearScreen() {
        if (!running) return;

        this.rows.clear();
        updateChars();
        updated();
    }

    @Override
    public void start() {
        if (!stopped) {
            this.running = true;
            updated();
        }
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

    @Override
    public ConsoleGraphics getConsoleGraphics() {
        return this.graphics;
    }

    public void updated() {
    }


}
