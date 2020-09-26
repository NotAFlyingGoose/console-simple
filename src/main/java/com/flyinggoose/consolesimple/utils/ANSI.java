package com.flyinggoose.consolesimple.utils;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum ANSI {
    BLACK(30),
    RED(31, Color.RED),
    GREEN(32, Color.GREEN),
    YELLOW(33, Color.YELLOW),
    BLUE(34, Color.BLUE),
    MAGENTA(35, Color.MAGENTA),
    CYAN(36, Color.CYAN),
    WHITE(37, Color.WHITE),
    RESET(0),
    ;

    public final static String front = "\u001b";
    private final int code;
    private Color color = Color.BLACK;

    ANSI(int code, Color color) {
        this(code);
        this.color = color;
    }

    ANSI(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public Color getColor() {
        return color;
    }

    public static ANSI getNearestAnsi(Color color) {
        List<ANSI> enumValues = new LinkedList<>(Arrays.asList(values()));

        enumValues.sort((d1, d2) -> (int) (colorDistance(d2.getColor(), color) - colorDistance(d1.getColor(), color)));

        return enumValues.get(enumValues.size()-1);
    }

    public static double colorDistance(Color c1, Color c2)
    {
        int red1 = c1.getRed();
        int red2 = c2.getRed();
        int rmean = (red1 + red2) >> 1;
        int r = red1 - red2;
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        return Math.sqrt((((512+rmean)*r*r)>>8) + 4*g*g + (((767-rmean)*b*b)>>8));
    }

    @Override
    public String toString() {
        return front + "[" + code + "m";
    }
}
