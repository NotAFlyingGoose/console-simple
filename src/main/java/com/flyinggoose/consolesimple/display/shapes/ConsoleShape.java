package com.flyinggoose.consolesimple.display.shapes;

import java.util.List;

public interface ConsoleShape {

    List<ConsoleShapePoint> getOuterPoints();

    List<ConsoleShapePoint> getFilledPoints();
}
