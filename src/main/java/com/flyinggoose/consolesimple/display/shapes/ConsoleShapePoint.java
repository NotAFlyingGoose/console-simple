package com.flyinggoose.consolesimple.display.shapes;

import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.utils.TextCharacter;

public class ConsoleShapePoint {
    public final ConsolePosition position;
    public final TextCharacter c;

    public ConsoleShapePoint(ConsolePosition position, TextCharacter c) {
        this.position = position;
        this.c = c;
    }

    public ConsolePosition getPosition() {
        return position;
    }

    public TextCharacter getChar() {
        return c;
    }
}
