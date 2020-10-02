package com.flyinggoose.consolesimple.utils;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class CharColor {

    public RGB toRGB() {
        if (this instanceof RGB) return (RGB) this;
        else return ((ANSI) this).color;
    }

    public ANSI toANSI() {
        if (this instanceof RGB) {
            List<ANSI> enumValues = new LinkedList<>();
            for (Field field : ANSI.class.getDeclaredFields()) {
                if (!field.getType().equals(ANSI.class)) continue;
                try {
                    enumValues.add((ANSI) field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            enumValues.removeIf(ansi -> ansi.color == null);

            enumValues.sort((d2, d1) -> (int) (colorDistance(d2.color) - colorDistance(d1.color)));

            return enumValues.get(0);
        } else return (ANSI) this;
    }

    public double colorDistance(RGB color) {
        int red1 = color.getRed();
        int red2 = toRGB().getRed();
        int rmean = (red1 + red2) >> 1;
        int r = red1 - red2;
        int g = color.getGreen() - toRGB().getGreen();
        int b = color.getBlue() - toRGB().getBlue();
        return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
    }

    public static class RGB extends CharColor {
        final int red;
        final int green;
        final int blue;

        public RGB(Color color) {
            this(color.getRed(), color.getGreen(), color.getBlue());
        }

        public RGB(int red, int green, int blue) {
            String errors = "";
            if (red > 255 || red < 0) errors += " red";
            if (green > 255 || green < 0) errors += " green";
            if (blue > 255 || blue < 0) errors += " blue";

            if (!errors.isEmpty())
                throw new IllegalArgumentException("Values of " + errors + " must be over 0 and under 255");

            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }

        @Override
        public String toString() {
            return "RGB[" + red + ", " + green + ", " + blue + "]";
        }
    }

    public static class ANSI extends CharColor {
        public final static ANSI BLACK = new ANSI(30, new RGB(Color.BLACK));
        public final static ANSI RED = new ANSI(31, new RGB(Color.RED));
        public final static ANSI GREEN = new ANSI(32, new RGB(Color.GREEN));
        public final static ANSI YELLOW = new ANSI(33, new RGB(Color.YELLOW));
        public final static ANSI BLUE = new ANSI(34, new RGB(Color.BLUE));
        public final static ANSI MAGENTA = new ANSI(35, new RGB(Color.MAGENTA));
        public final static ANSI CYAN = new ANSI(36, new RGB(Color.CYAN));
        public final static ANSI WHITE = new ANSI(37, new RGB(Color.WHITE));
        public final static ANSI RESET = new ANSI(0, null);

        public final static String front = "\u001b[";
        private final int code;
        private RGB color;

        private ANSI(int code, RGB color) {
            this.code = code;
            this.color = color;
        }

        public int getCode() {
            return this.code;
        }

        @Override
        public String toString() {
            return front + code + "m";
        }
    }
}
