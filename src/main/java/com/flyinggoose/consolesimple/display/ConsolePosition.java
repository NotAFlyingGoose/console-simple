package com.flyinggoose.consolesimple.display;

public class ConsolePosition implements Cloneable {
    private int x;
    private int y;

    public ConsolePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ConsolePosition createOffset(int xOffset, int yOffset) {
        return new ConsolePosition(this.x + xOffset, this.y + yOffset);
    }

    public ConsolePosition clone() {
        return new ConsolePosition(this.x, this.y);
    }

    @Override
    public String toString() {
        return "ConsolePosition[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConsolePosition pos) {
            return x == pos.x && y == pos.y;
        }
        return false;
    }
}
