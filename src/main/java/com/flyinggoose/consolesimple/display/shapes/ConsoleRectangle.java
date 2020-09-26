package com.flyinggoose.consolesimple.display.shapes;

import com.flyinggoose.consolesimple.display.ConsoleCharacter;
import com.flyinggoose.consolesimple.display.ConsolePosition;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRectangle implements ConsoleShape {
    private ConsolePosition pos;
    private int width;
    private int height;
    private char fill;
    private char topLeft;
    private char topRight;
    private char bottomLeft;
    private char bottomRight;
    private char horizontal;
    private char vertical;

    public ConsoleRectangle(ConsolePosition pos, int width, int height, char fill, char topLeft, char topRight, char bottomLeft, char bottomRight, char horizontal, char vertical) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public List<ConsoleShapePoint> getOuterPoints() {
        List<ConsoleShapePoint> points = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            ConsolePosition topPos = new ConsolePosition(x + pos.getX(), pos.getY());
            ConsolePosition bottomPos = new ConsolePosition(x + pos.getX(), height + pos.getY() - 1);

            points.add(new ConsoleShapePoint(topPos, horizontal));

            points.add(new ConsoleShapePoint(bottomPos, horizontal));

            if (x == 0 || x == width - 1) {
                for (int y = 1; y < height - 1; y++) {
                    ConsolePosition sidePos = new ConsolePosition(x + pos.getX(), y + pos.getY());

                    points.add(new ConsoleShapePoint(sidePos, vertical));
                }
                ConsolePosition topCornerPos = new ConsolePosition(x + pos.getX(), pos.getY());
                ConsolePosition bottomCornerPos = new ConsolePosition(x + pos.getX(), height + pos.getY() - 1);
                if (x == 0) {
                    points.add(new ConsoleShapePoint(topCornerPos, topLeft));

                    points.add(new ConsoleShapePoint(bottomCornerPos, bottomLeft));
                } else {
                    points.add(new ConsoleShapePoint(topCornerPos, topRight));

                    points.add(new ConsoleShapePoint(bottomCornerPos, bottomRight));
                }
            }
        }
        return points;
    }

    @Override
    public List<ConsoleShapePoint> getFilledPoints() {
        List<ConsoleShapePoint> points = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            ConsolePosition topPos = new ConsolePosition(x + pos.getX(), pos.getY());
            ConsolePosition bottomPos = new ConsolePosition(x + pos.getX(), height + pos.getY() - 1);

            points.add(new ConsoleShapePoint(topPos, horizontal));

            points.add(new ConsoleShapePoint(bottomPos, horizontal));

            if (x == 0 || x == width - 1) {
                for (int y = 1; y < height - 1; y++) {
                    ConsolePosition sidePos = new ConsolePosition(x + pos.getX(), y + pos.getY());

                    points.add(new ConsoleShapePoint(sidePos, vertical));
                }
                ConsolePosition topCornerPos = new ConsolePosition(x + pos.getX(), pos.getY());
                ConsolePosition bottomCornerPos = new ConsolePosition(x + pos.getX(), height + pos.getY() - 1);
                if (x == 0) {
                    points.add(new ConsoleShapePoint(topCornerPos, topLeft));

                    points.add(new ConsoleShapePoint(bottomCornerPos, bottomLeft));
                } else {
                    points.add(new ConsoleShapePoint(topCornerPos, topRight));

                    points.add(new ConsoleShapePoint(bottomCornerPos, bottomRight));
                }
            } else {
                for (int y = 1; y < height - 1; y++) {
                    ConsolePosition sidePos = new ConsolePosition(x + pos.getX(), y + pos.getY());

                    points.add(new ConsoleShapePoint(sidePos, fill));
                }
            }
        }
        return points;
    }

    public ConsolePosition getPos() {
        return pos;
    }

    public void setPos(ConsolePosition pos) {
        this.pos = pos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char getFill() {
        return fill;
    }

    public void setFill(char fill) {
        this.fill = fill;
    }

    public char getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(char topLeft) {
        this.topLeft = topLeft;
    }

    public char getTopRight() {
        return topRight;
    }

    public void setTopRight(char topRight) {
        this.topRight = topRight;
    }

    public char getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(char bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public char getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(char bottomRight) {
        this.bottomRight = bottomRight;
    }

    public char getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(char horizontal) {
        this.horizontal = horizontal;
    }

    public char getVertical() {
        return vertical;
    }

    public void setVertical(char vertical) {
        this.vertical = vertical;
    }

    public static class Basic extends ConsoleRectangle {
        public Basic(ConsolePosition pos, int width, int height, char fill) {
            super(pos, width, height, fill, fill, fill, fill, fill, fill, fill);
        }
    }

    public static class Box extends ConsoleRectangle {
        public Box(ConsolePosition pos, int width, int height) {
            super(pos, width, height, ' ', ConsoleCharacter.BOX_RIGHT_DOWN, ConsoleCharacter.BOX_LEFT_DOWN, ConsoleCharacter.BOX_RIGHT_UP, ConsoleCharacter.BOX_LEFT_UP, ConsoleCharacter.BOX_HORIZONTAL, ConsoleCharacter.BOX_VERTICAL);
        }
    }

    public static class DoubleBox extends ConsoleRectangle {
        public DoubleBox(ConsolePosition pos, int width, int height) {
            super(pos, width, height, ' ', ConsoleCharacter.BOX_DOUBLE_RIGHT_DOWN, ConsoleCharacter.BOX_DOUBLE_LEFT_DOWN, ConsoleCharacter.BOX_DOUBLE_RIGHT_UP, ConsoleCharacter.BOX_DOUBLE_LEFT_UP, ConsoleCharacter.BOX_DOUBLE_HORIZONTAL, ConsoleCharacter.BOX_DOUBLE_VERTICAL);
        }
    }
}
