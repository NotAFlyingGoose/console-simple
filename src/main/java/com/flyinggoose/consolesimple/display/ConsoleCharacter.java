package com.flyinggoose.consolesimple.display;

import com.flyinggoose.consolesimple.utils.TextCharacter;

import java.awt.*;

public class ConsoleCharacter {
    public static final char FULL_BLOCK = '█'; // U+2588

    //boxes
    //single
    public static final char BOX_VERTICAL = '│'; // U+2502
    public static final char BOX_VERTICAL_RIGHT = '├'; // U+251C
    public static final char BOX_VERTICAL_LEFT = '┤'; // U+2524
    public static final char BOX_CROSS = '┼'; // U+253C
    public static final char BOX_HORIZONTAL = '─'; // U+2500
    public static final char BOX_HORIZONTAL_UP = '┴'; // U+2534
    public static final char BOX_HORIZONTAL_DOWN = '┬'; // U+252C
    public static final char BOX_RIGHT_DOWN = '┌'; // U+250C
    public static final char BOX_LEFT_DOWN = '┐'; // U+2510
    public static final char BOX_RIGHT_UP = '└'; // U+2514
    public static final char BOX_LEFT_UP = '┘'; // U+2518
    //double
    public static final char BOX_DOUBLE_VERTICAL = '║'; // U+2551
    public static final char BOX_DOUBLE_VERTICAL_RIGHT = '╠'; // U+2560
    public static final char BOX_DOUBLE_VERTICAL_LEFT = '╣'; // U+2563
    public static final char BOX_DOUBLE_CROSS = '╬'; // U+256C
    public static final char BOX_DOUBLE_HORIZONTAL = '═'; // U+2550
    public static final char BOX_DOUBLE_HORIZONTAL_UP = '╩'; // U+2569
    public static final char BOX_DOUBLE_HORIZONTAL_DOWN = '╦'; // U+2566
    public static final char BOX_DOUBLE_RIGHT_DOWN = '╔'; // U+250C
    public static final char BOX_DOUBLE_LEFT_DOWN = '╗'; // U+2510
    public static final char BOX_DOUBLE_RIGHT_UP = '╚'; // U+2514
    public static final char BOX_DOUBLE_LEFT_UP = '╝'; // U+2518

    final ConsolePosition pos;
    Color foreground = Color.WHITE;
    Color background = Color.BLACK;
    TextCharacter character;

    public ConsoleCharacter(TextCharacter character, ConsolePosition pos) {
        this.character = character;
        this.pos = pos;
    }

    public TextCharacter getTextCharacter() {
        return character;
    }

    public void setTextCharacter(TextCharacter character) {
        this.character = character;
    }

    public ConsolePosition getPos() {
        return pos;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

}
