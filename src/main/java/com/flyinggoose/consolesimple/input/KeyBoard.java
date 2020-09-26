package com.flyinggoose.consolesimple.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private final boolean[] keyChecks = new boolean[256];
    private final boolean[] keys = new boolean[256];
    private StringBuilder typed = new StringBuilder();
    private String lastLine = null;
    private StringBuilder currentLine = new StringBuilder();

    public boolean isKeyClicked(char c) {
        return isKeyClicked(KeyEvent.getExtendedKeyCodeForChar(c));
    }

    public boolean isKeyClicked(int code) {
        if (keys[code] && !keyChecks[code]) {
            keyChecks[code] = true;
            return true;
        }
        return false;
    }

    public boolean isKeyDown(char c) {
        return isKeyDown(KeyEvent.getExtendedKeyCodeForChar(c));
    }

    public boolean isKeyDown(int code) {
        return keys[code];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            this.lastLine = this.currentLine.toString();
            this.currentLine = new StringBuilder();
            this.typed.append('\n');
        } else if (isNormalCharacter(e.getKeyChar())) {
            if (keys[KeyEvent.VK_SHIFT]) {
                this.currentLine.append(Character.toUpperCase(e.getKeyChar()));
                this.typed.append(Character.toUpperCase(e.getKeyChar()));
            } else {
                this.currentLine.append(e.getKeyChar());
                this.typed.append(e.getKeyChar());
            }
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            int delIndex = this.currentLine.length() - 1;
            if (delIndex > 0 && delIndex < this.currentLine.length()) this.currentLine.deleteCharAt(delIndex);
            this.typed.append('\b');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        keyChecks[e.getKeyCode()] = false;
    }

    public String getNextLine() {
        if (lastLine == null) return null;

        String result = new String(this.lastLine.toCharArray());
        lastLine = null;
        return result;
    }

    public String getCurrentLine() {
        return currentLine.toString();
    }

    public String getAllTyped() {
        return typed.toString();
    }

    public boolean isNormalCharacter(char c) {
        if (Character.isLetterOrDigit(c)) return true;
        else if (Character.isWhitespace(c)) return true;
        else {
            return switch (c) {
                case '~', '!', '@', '#', '$', '%',
                        '^', '&', '*', '(', ')', '-',
                        '=', '[', ']', '{', '}', ';',
                        ':', '"', '\'', ',', '<', '.',
                        '>', '/', '?', '\\', '|', '`', '+' -> true;
                default -> false;
            };
        }
    }

}
