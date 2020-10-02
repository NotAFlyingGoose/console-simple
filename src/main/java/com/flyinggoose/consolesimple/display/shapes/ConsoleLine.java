package com.flyinggoose.consolesimple.display.shapes;

import com.flyinggoose.consolesimple.display.ConsolePosition;
import com.flyinggoose.consolesimple.utils.TextCharacter;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLine implements ConsoleShape {
    private ConsolePosition start;
    private ConsolePosition end;
    private TextCharacter c;

    public ConsoleLine(ConsolePosition start, ConsolePosition end, TextCharacter fill) {
        this.start = start;
        this.end = end;
        this.c = fill;
    }

    private static double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    private static ConsolePosition lerpPoint(ConsolePosition p0, ConsolePosition p1, double t) {
        return new ConsolePosition((int) Math.round(lerp(p0.getX(), p1.getX(), t)),
                (int) Math.round(lerp(p0.getY(), p1.getY(), t)));
    }

    private static double diagonalDistance(ConsolePosition p0, ConsolePosition p1) {
        double dx = p1.getX() - p0.getX(), dy = p1.getY() - p0.getY();
        return Math.max(Math.abs(dx), Math.abs(dy));
    }

    private static List<ConsoleShapePoint> createPoints(ConsolePosition p0, ConsolePosition p1, TextCharacter c) {
        List<ConsoleShapePoint> points = new ArrayList<>();
        var N = diagonalDistance(p0, p1);
        for (int step = 0; step <= N; step++) {
            double t = step / N;
            points.add(new ConsoleShapePoint(lerpPoint(p0, p1, t), c));
        }
        return points;
    }

    @Override
    public List<ConsoleShapePoint> getOuterPoints() {
        return createPoints(this.start, this.end, this.c);
    }

    @Override
    public List<ConsoleShapePoint> getFilledPoints() {
        return createPoints(this.start, this.end, this.c);
    }

    public ConsolePosition getStart() {
        return start;
    }

    public void setStart(ConsolePosition start) {
        this.start = start;
    }

    public ConsolePosition getEnd() {
        return end;
    }

    public void setEnd(ConsolePosition end) {
        this.end = end;
    }

    public TextCharacter getFill() {
        return c;
    }

    public void setFill(TextCharacter c) {
        this.c = c;
    }
}
