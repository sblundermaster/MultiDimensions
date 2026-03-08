package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.*;

import java.util.ArrayList;

public class Space {
    public int Dimensions;
    public ArrayList<Object> Objects;
    public ArrayList<Point> Points;
    public ArrayList<Line> Lines;
    public ArrayList<Flat> Flats;
    public Space(int dimensions) throws UnsupportedDimensionsCountException {
        if (dimensions < 1) {
            throw new UnsupportedDimensionsCountException();
        }
        this.Dimensions = dimensions;
        this.Objects = new ArrayList<>();
        this.Points = new ArrayList<>();
        this.Lines = new ArrayList<>();
        this.Flats = new ArrayList<>();
    }
    public void AddObject(Object object) {
        this.Objects.add(object);
    }
    public void AddPoint(Point point) throws PointsUnsupportedException {
        if (this.Dimensions < 2) {
            throw new PointsUnsupportedException();
        }
        this.Points.add(point);
    }
    public void AddLine(Line line) throws LinesUnsupportedException {
        if (this.Dimensions < 3) {
            throw new LinesUnsupportedException();
        }
        this.Lines.add(line);
    }
    public void AddFlat(Flat flat) throws FlatsUnsupportedException {
        if (this.Dimensions < 4) {
            throw new FlatsUnsupportedException();
        }
        this.Flats.add(flat);
    }
    public void ClearEverything() {
        this.Objects.clear();
        this.Points.clear();
        this.Lines.clear();
        this.Flats.clear();
    }
    public boolean IsPointInObject(Point point, int object) {
        try {
            Rational testing = new Rational(0, 1);
            ArrayList<Rational> params = this.Objects.get(object).Parameters;
            ArrayList<Rational> cords = point.Cords;
            for (int i = 0; i < cords.size(); i++) {
                testing.Addict(Rational.Multiplication(params.get(i), cords.get(i)));
            }
            testing.Rationalize();
            System.out.println(params.get(params.size() - 1).up + "/" + params.get(params.size() - 1).down);
            System.out.println(testing.up + "/" + testing.down);
            return testing.Equal(Rational.Multiplication(params.get(params.size() - 1), new Rational(-1, 1)));
        } catch (DivideByZeroException e) {
            return false;
        }
    }
    public boolean IsPointInObject(int point, int object) {
        return IsPointInObject(this.Points.get(point), object);
    }
    public boolean IsLineInObject(Line line, int object) {
        return (this.IsPointInObject(line.First, object) && this.IsPointInObject(line.Second, object));
    }
    public boolean IsLineInObject(int line, int object) {
        return IsLineInObject(this.Lines.get(line), object);
    }
    public boolean IsFlatInObject(Flat flat, int object) {
        return (this.IsPointInObject(flat.First, object) && this.IsPointInObject(flat.Second, object) && this.IsPointInObject(flat.Third, object));
    }
    public boolean IsFlatInObject(int flat, int object) {
        return IsFlatInObject(this.Flats.get(flat), object);
    }
    public boolean IsPointInLine(int point, int line) {
        try {
            return IsPointInLine(this.Points.get(point), this.Lines.get(line));
        } catch (DifferentPointsCordsCountsException e) {
            return false;
        }
    }
    public boolean SameObjects(int first, int second) throws SimilarPointsException {
        try {
            return SameObjects(this.Objects.get(first), this.Objects.get(second));
        } catch (DifferentObjectsDimensionsCountsException e) {
            return false;
        }
    }
    public boolean ParallelObjects(int first, int second) throws SimilarPointsException {
        try {
            return ParallelObjects(this.Objects.get(first), this.Objects.get(second));
        } catch (DifferentObjectsDimensionsCountsException e) {
            return false;
        }
    }
    public boolean SimilarObjects(int first, int second) throws SimilarPointsException {
        try {
            return SimilarObjects(this.Objects.get(first), this.Objects.get(second));
        } catch (DifferentObjectsDimensionsCountsException e) {
            return false;
        }
    }
    public static boolean SameObjects(Object first, Object second) throws SimilarPointsException, DifferentObjectsDimensionsCountsException {
        if (!SimilarObjects(first, second)) {
            return false;
        }
        int l = first.Dimensions;
        try {
            if (Rational.Same(Rational.Multiplication(first.Parameters.get(l), second.Parameters.get(l - 1)),
                    Rational.Multiplication(first.Parameters.get(l - 1), second.Parameters.get(l)))) {
                return true;
            }
        } catch (DivideByZeroException e) {
            return false;
        }
        return false;
    }
    public static boolean ParallelObjects(Object first, Object second) throws SimilarPointsException, DifferentObjectsDimensionsCountsException {
        if (!SimilarObjects(first, second)) {
            return false;
        }
        int l = first.Dimensions;
        try {
            if (!Rational.Same(Rational.Multiplication(first.Parameters.get(l), second.Parameters.get(l - 1)),
                    Rational.Multiplication(first.Parameters.get(l - 1), second.Parameters.get(l)))) {
                return true;
            }
        } catch (DivideByZeroException e) {
            return false;
        }
        return false;
    }
    public static boolean SimilarObjects(Object first, Object second) throws SimilarPointsException, DifferentObjectsDimensionsCountsException {
        if (first.Dimensions != second.Dimensions) {
            throw new DifferentObjectsDimensionsCountsException();
        }
        if (first.Dimensions < 1) {
            throw new SimilarPointsException();
        }
        try {
            for (int i = 0; i < first.Dimensions - 1; i++) {
                if (!Rational.Same(Rational.Multiplication(first.Parameters.get(i), second.Parameters.get(i + 1)),
                        Rational.Multiplication(first.Parameters.get(i + 1), second.Parameters.get(i)))) {
                    return false;
                }
            }
        } catch (DivideByZeroException e) {
            return false;
        }
        return true;
    }
    public static boolean IsPointInLine(Point point, Line line) throws DifferentPointsCordsCountsException {
        try {
            return OnTheSameLine(new Point[] { point, line.First, line.Second });
        } catch (NotEnoughPointsException e) {
            return false;
        }
    }
    public static boolean OnTheSameLine(Point[] points) throws NotEnoughPointsException, DifferentPointsCordsCountsException {
        if (points.length < 2) {
            throw new NotEnoughPointsException();
        }
        if (points.length == 2) {
            return true;
        }
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].Dimensions != points[i + 1].Dimensions) {
                throw new DifferentPointsCordsCountsException();
            }
        }
        Rational[] differences = new Rational[points.length];
        int reali = points.length;
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].Equal(points[i + 1])) {
                continue;
            }
            for (int j = 0; j < points[0].Dimensions; j++) {
                try {
                    differences[j] = Rational.Subtraction(points[i].Cords.get(j), points[i + 1].Cords.get(j));
                } catch (DivideByZeroException e) {}
            }
            reali = i + 1;
            break;
        }
        try {
            for (int j = 0; j < points[0].Dimensions - 1; j++) {
                for (int i = reali; i < points.length - 1; i++) {
                    if (!Rational.Same(Rational.Multiplication(differences[j], Rational.Subtraction(points[i].Cords.get(j + 1), points[i + 1].Cords.get(j + 1))),
                            Rational.Multiplication(differences[j + 1], Rational.Subtraction(points[i].Cords.get(j), points[i + 1].Cords.get(j))))) {
                        return false;
                    }
                }
            }
        } catch (DivideByZeroException e) {
            return false;
        }
        return true;
    }
}