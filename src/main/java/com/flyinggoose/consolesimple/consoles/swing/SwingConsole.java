package com.flyinggoose.consolesimple.consoles.swing;

import com.flyinggoose.consolesimple.utils.TextCharacter;
import com.flyinggoose.consolesimple.consoles.Console;
import com.flyinggoose.consolesimple.display.ConsoleCharacter;
import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.display.ConsoleSize;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import java.util.List;

public class SwingConsole extends Canvas implements Console {
    private final SwingConsoleDisplay display;
    SwingConsoleFontConfig fontConfig;
    List<List<ConsoleCharacter>> rows = new LinkedList<>();
    private boolean running = false;
    private ConsoleSize consoleSize = new ConsoleSize(10, 10);
    private ConsolePosition lastEdit = new ConsolePosition(0, 0);

    public SwingConsole(String title) {
        this(title, new ConsoleSize(15, 15));
    }

    public SwingConsole(String title, ConsoleSize size) {
        this(title, size, SwingConsoleFontConfig.getSystemDefault());
    }

    public SwingConsole(String title, ConsoleSize size, SwingConsoleFontConfig fontConfig) {
        this.fontConfig = fontConfig;

        this.display = new SwingConsoleDisplay(title, this);
        this.display.cellWidth = fontConfig.getFontWidth();
        this.display.cellHeight = fontConfig.getFontHeight();
        this.display.getWindow().setMinimumSize(new Dimension(fontConfig.getFontWidth(), fontConfig.getFontHeight()));

        setConsoleSize(size);
        this.display.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                if (SwingConsole.this.running && !new ConsoleSize((int) Math.floor(display.getWidth() / (double) display.cellWidth) - 1, (int) Math.floor(display.getHeight() / (double) display.cellHeight) - 1).equals(SwingConsole.this.consoleSize)) {
                    updateChars();
                }
            }
        });
    }

    @Override
    public synchronized void start() {
        running = true;
        this.display.start();
    }

    @Override
    public synchronized void stop() {
        running = false;
        this.display.stop();
    }

    private void updateChars() {
        consoleSize = new ConsoleSize((int) Math.floor(this.display.getWindow().getWidth() / (double) display.cellWidth) - 1, (int) Math.floor(this.display.getWindow().getHeight() / (double) display.cellHeight) - 1);

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
        if (getConsoleSize().isValid(pos)) setCharAt(pos, ' ');
    }

    @Override
    public void resetBackgroundAt(ConsolePosition pos) {
        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setBackground(Color.BLACK);
    }

    @Override
    public void resetForegroundAt(ConsolePosition pos) {
        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setForeground(Color.WHITE);
    }

    @Override
    public void setCharAt(ConsolePosition pos, char c) {
        if (getConsoleSize().isValid(pos)) {
            this.lastEdit = pos;
            rows.get(pos.getY()).get(pos.getX()).setTextCharacter(new TextCharacter(c, false, false));
        }
    }

    @Override
    public void setBackgroundAt(ConsolePosition pos, Color c) {
        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setBackground(c);
    }

    @Override
    public void setForegroundAt(ConsolePosition pos, Color c) {
        if (getConsoleSize().isValid(pos)) rows.get(pos.getY()).get(pos.getX()).setForeground(c);
    }

    @Override
    public char getCharAt(ConsolePosition pos) {
        if (getConsoleSize().isValid(pos))
            return rows.get(pos.getY()).get(pos.getX()).getTextCharacter().getCharacter();
        return ' ';
    }

    @Override
    public Color getBackgroundAt(ConsolePosition pos) {
        if (getConsoleSize().isValid(pos)) return rows.get(pos.getY()).get(pos.getX()).getBackground();
        return Color.BLACK;
    }

    @Override
    public Color getForegroundAt(ConsolePosition pos) {
        if (getConsoleSize().isValid(pos)) return rows.get(pos.getY()).get(pos.getX()).getForeground();
        return Color.WHITE;
    }

    @Override
    public ConsolePosition getLastEdit() {
        return this.lastEdit;
    }

    @Override
    public ConsoleSize getConsoleSize() {
        return this.consoleSize;
    }

    @Override
    public void setConsoleSize(ConsoleSize consoleSize) {
        this.display.getWindow().setSize(consoleSize.getWidth() * display.cellWidth, consoleSize.getHeight() * display.cellHeight);
        this.display.getWindow().setPreferredSize(this.display.getSize());
        this.display.getWindow().setLocationRelativeTo(null);
        updateChars();
    }

    @Override
    public void clearScreen() {
        this.rows.clear();
        updateChars();
    }

    @Override
    public String getNextLine() {
        return display.getKeyboard().getNextLine();
    }

    @Override
    public String getLastLine() {
        return display.getKeyboard().getCurrentLine();
    }

    @Override
    public String getAllLines() {
        return display.getKeyboard().getAllTyped();
    }

    @Override
    public boolean isPressing(char key) {
        return display.getKeyboard().isKeyDown(key);
    }

    @Override
    public boolean isPressing(int key) {
        return display.getKeyboard().isKeyDown(key);
    }
}
