package com.flyinggoose.consolesimple.display;

import com.flyinggoose.consolesimple.consoles.Console;

import java.util.HashSet;
import java.util.Set;

public class ConsoleBuffer {
    final Console console;
    Set<ConsoleCell> buffer = new HashSet<>();

    public ConsoleBuffer(Console console) {
        this.console = console;
    }

    public void flush() {
        for (ConsoleCell c : buffer) {
            this.console.setCharAt(c.getPos(), c.getTextCharacter());
            this.console.setBackgroundAt(c.getPos(), c.getBackground());
            this.console.setForegroundAt(c.getPos(), c.getForeground());
        }
        buffer.clear();
    }

    public void push(ConsoleCell character) {
        buffer.removeIf(c -> c.getPos().equals(character.getPos()));
        buffer.add(character);
    }

    public ConsoleCell get(ConsolePosition position) {
        for (ConsoleCell c : buffer) {
            if (c.getPos().equals(position)) return c;
        }
        return null;
    }
}
