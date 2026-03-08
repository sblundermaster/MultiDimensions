package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.*;

public class Flat {
    public int Dimensions;
    public Point First;
    public Point Second;
    public Point Third;
    public Flat(Point first, Point second, Point third) throws DifferentPointsCordsCountsException, NotSingleFlatException {
        try {
            // Если точки лежат на одной прямой, то плоскость, которую можно задать этими точками, не единственная.
            if (Space.OnTheSameLine(new Point[]{ first, second, third })) {
                throw new NotSingleFlatException();
            }
        } catch (NotEnoughPointsException e) {}
        this.First = first;
        this.Second = second;
        this.Third = third;
    }
}
