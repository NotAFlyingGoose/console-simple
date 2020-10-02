package com.flyinggoose.consolesimple.display;

import com.flyinggoose.consolesimple.utils.CharColor;
import com.flyinggoose.consolesimple.utils.TextCharacter;
import com.flyinggoose.consolesimple.consoles.Console;
import com.flyinggoose.consolesimple.display.shapes.ConsoleLine;
import com.flyinggoose.consolesimple.display.shapes.ConsoleRectangle;
import com.flyinggoose.consolesimple.display.shapes.ConsoleShape;
import com.flyinggoose.consolesimple.display.shapes.ConsoleShapePoint;

import java.util.Objects;

public class ConsoleGraphics {
    private final Console console;
    private final ConsoleBuffer buffer;
    private CharColor backgroundColor = null;
    private CharColor foregroundColor = null;

    public ConsoleGraphics(Console console) {
        this.console = console;
        this.buffer = new ConsoleBuffer(console);
    }

    public CharColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(CharColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public CharColor getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(CharColor foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public ConsolePosition drawString(ConsolePosition pos, String text) {
        ConsolePosition hand = pos.clone();
        for (char c : text.toCharArray()) {
            switch (c) {
                case '\n' -> hand = new ConsolePosition(pos.getX(), hand.getY() + 1);
                case '\t' -> hand = hand.createOffset(5, 0);
                default -> {
                    // create character
                    TextCharacter tc = new TextCharacter(c, false, false);
                    ConsoleCell character = new ConsoleCell(tc, hand);

                    //set color
                    color(character);

                    // push character to the buffer
                    buffer.push(character);

                    //move to the right
                    hand = hand.createOffset(1, 0);
                    if (console.getConsoleSize().getWidth() < hand.getX())
                        hand = new ConsolePosition(pos.getX(), hand.getY() + 1);
                }
            }
        }
        return hand;
    }

    public void drawRectangle(ConsolePosition pos, int width, int height, TextCharacter c) {
        draw(new ConsoleRectangle.Basic(pos, width, height, c));
    }

    public void fillRectangle(ConsolePosition pos, int width, int height, TextCharacter c) {
        fill(new ConsoleRectangle.Basic(pos, width, height, c));
    }

    public void drawChar(ConsolePosition pos, TextCharacter c) {
        ConsoleCell drawChar = new ConsoleCell(c, pos);
        color(drawChar);
        buffer.push(drawChar);
    }

    public void drawLine(ConsolePosition start, ConsolePosition end, TextCharacter c) {
        draw(new ConsoleLine(start, end, c));
    }

    public void draw(ConsoleShape shape) {
        for (ConsoleShapePoint point : shape.getOuterPoints()) {
            drawChar(point.getPosition(), point.getChar());
        }
    }

    public void fill(ConsoleShape shape) {
        for (ConsoleShapePoint point : shape.getFilledPoints()) {
            drawChar(point.getPosition(), point.getChar());
        }
    }

    public void color(ConsoleCell character) {
        character.setForeground(Objects.requireNonNullElse(foregroundColor, CharColor.ANSI.WHITE));
        character.setBackground(Objects.requireNonNullElse(backgroundColor, CharColor.ANSI.BLACK));
    }

    public void flush() {
        buffer.flush();
    }
}
