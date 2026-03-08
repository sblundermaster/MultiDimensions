package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.*;

import java.util.ArrayList;

public class Line {
    public int Dimensions;
    public Point First;
    public Point Second;
    public Line(Point first, Point second) throws DifferentPointsCordsCountsException, NotSingleLineException {
        if (first.Cords.size() != second.Cords.size()) {
            throw new DifferentPointsCordsCountsException();
        }
        boolean OK = false;
        for (int i = 0; i < first.Cords.size(); i++) {
            // Если координаты точек равны, то прямая, которую можно провести через эти точки, не единственная.
            if (first.Cords.get(i) != second.Cords.get(i)) {
                OK = true;
                break;
            }
        }
        if (!OK) {
            throw new NotSingleLineException();
        }
        this.Dimensions = first.Cords.size();
        this.First = first;
        this.Second = second;
    }
}
