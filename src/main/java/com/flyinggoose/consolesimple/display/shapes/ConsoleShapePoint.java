package com.flyinggoose.consolesimple.display.shapes;

import com.flyinggoose.consolesimple.display.ConsolePosition;

public class ConsoleShapePoint {
    public final ConsolePosition position;
    public final char c;

    public ConsoleShapePoint(ConsolePosition position, char c) {
        this.position = position;
        this.c = c;
    }

    public ConsolePosition getPosition() {
        return position;
    }

    public char getChar() {
        return c;
    }
}
