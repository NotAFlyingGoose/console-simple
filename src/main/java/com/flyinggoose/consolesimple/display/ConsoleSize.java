package com.flyinggoose.consolesimple.display;

public class ConsoleSize {
    final int width;
    final int height;

    public ConsoleSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isValid(ConsolePosition position) {
        return position.getX() < width && position.getY() < height;
    }

    @Override
    public String toString() {
        return "ConsoleSize[" + width + ", " + height + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConsoleSize size) {
            return width == size.width && height == size.height;
        }
        return false;
    }
}
