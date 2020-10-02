package com.flyinggoose.consolesimple.display;

import com.flyinggoose.consolesimple.utils.CharColor;
import com.flyinggoose.consolesimple.utils.TextCharacter;

public class ConsoleCell {
    final ConsolePosition pos;
    CharColor foreground = CharColor.ANSI.WHITE;
    CharColor background = CharColor.ANSI.BLACK;
    TextCharacter character;

    public ConsoleCell(TextCharacter character, ConsolePosition pos) {
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

    public CharColor getForeground() {
        return foreground;
    }

    public void setForeground(CharColor foreground) {
        this.foreground = foreground;
    }

    public CharColor getBackground() {
        return background;
    }

    public void setBackground(CharColor background) {
        this.background = background;
    }

}
