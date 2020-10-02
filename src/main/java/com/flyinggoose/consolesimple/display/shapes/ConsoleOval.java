package com.flyinggoose.consolesimple.display.shapes;

import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.utils.TextCharacter;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOval implements ConsoleShape {
    private ConsolePosition pos;
    private double width;
    private double height;
    private TextCharacter fill;

    public ConsoleOval(ConsolePosition pos, int width, int height, TextCharacter fill) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.fill = fill;
    }

    @Override
    public List<ConsoleShapePoint> getOuterPoints() {
        List<ConsoleShapePoint> points = new ArrayList<>();
        double i, angle, x1, y1;

        for (i = 0; i < 360; i += 1) {
            angle = i;
            x1 = (width / 2) * Math.cos(angle * Math.PI / 180);
            y1 = (height / 2) * Math.sin(angle * Math.PI / 180);

            int ElX = (int) Math.round(pos.getX() + x1);
            int ElY = (int) Math.round(pos.getY() + y1);
            points.add(new ConsoleShapePoint(new ConsolePosition(ElX, ElY), this.fill));
        }

        return points;
    }

    @Override
    public List<ConsoleShapePoint> getFilledPoints() {
        List<ConsoleShapePoint> points = new ArrayList<>();
        double top = pos.getY() - (height / 2.0),
                bottom = pos.getY() + (height / 2.0),
                left = pos.getX() - (width / 2.0),
                right = pos.getX() + (width / 2.0);

        for (double y = top; y <= bottom; y++) {
            for (double x = left; x <= right; x++) {
                if (insideOval(new ConsolePosition((int) Math.floor(x), (int) Math.floor(y)))) {
                    points.add(new ConsoleShapePoint(new ConsolePosition((int) Math.floor(x), (int) Math.floor(y)), fill));
                }
            }
        }

        return points;
    }

    private boolean insideOval(ConsolePosition tile) {
        return insideCircle(tile, width / 2.0) || insideCircle(tile, height / 2.0);
    }

    private boolean insideCircle(ConsolePosition tile, double radius) {
        float dx = pos.getX() - tile.getX(),
                dy = pos.getY() - tile.getY();
        float distance_squared = dx * dx + dy * dy;
        return distance_squared <= radius * radius;
    }

    public ConsolePosition getPos() {
        return pos;
    }

    public void setPos(ConsolePosition pos) {
        this.pos = pos;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public TextCharacter getFill() {
        return fill;
    }

    public void setFill(TextCharacter fill) {
        this.fill = fill;
    }
}
