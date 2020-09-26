package com.flyinggoose.consolesimple.display;

import com.flyinggoose.consolesimple.utils.TextCharacter;
import com.flyinggoose.consolesimple.consoles.Console;
import com.flyinggoose.consolesimple.display.shapes.ConsoleLine;
import com.flyinggoose.consolesimple.display.shapes.ConsoleRectangle;
import com.flyinggoose.consolesimple.display.shapes.ConsoleShape;
import com.flyinggoose.consolesimple.display.shapes.ConsoleShapePoint;

import java.awt.*;

public class ConsoleGraphics {
    private final Console console;
    private final ConsoleBuffer buffer;
    private Color backgroundColor = null;
    private Color foregroundColor = null;

    public ConsoleGraphics(Console console) {
        this.console = console;
        this.buffer = new ConsoleBuffer(console);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
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
                    ConsoleCharacter character = new ConsoleCharacter(tc, hand);

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

    public void drawRectangle(ConsolePosition pos, int width, int height, char c) {
        draw(new ConsoleRectangle.Basic(pos, width, height, c));
    }

    public void fillRectangle(ConsolePosition pos, int width, int height, char c) {
        fill(new ConsoleRectangle.Basic(pos, width, height, c));
    }

    public void drawChar(ConsolePosition pos, char c) {
        ConsoleCharacter drawChar = new ConsoleCharacter(new TextCharacter(c, false, false), pos);
        color(drawChar);
        buffer.push(drawChar);
    }

    public void drawLine(ConsolePosition start, ConsolePosition end, char c) {
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

    public void color(ConsoleCharacter character) {
        if (foregroundColor != null) character.setForeground(foregroundColor);
        else character.setForeground(Color.WHITE);
        if (backgroundColor != null) character.setBackground(backgroundColor);
        else character.setBackground(Color.BLACK);
    }

    public void flush() {
        buffer.flush();
    }
}
