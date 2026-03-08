package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.DifferentPointsCordsCountsException;
import MultiDimensions.SecondVersion.Exceptions.DivideByZeroException;
import MultiDimensions.SecondVersion.Exceptions.InvalidDimensionsCountException;

import java.util.ArrayList;

public class Point {
    public int Dimensions;
    public ArrayList<Rational> Cords;
    public Point(int dimensions, ArrayList<Rational> cords) throws InvalidDimensionsCountException {
        if (cords.size() != dimensions) {
            throw new InvalidDimensionsCountException();
        }
        this.Dimensions = dimensions;
        this.Cords = cords;
    }
    public Point(int dimensions, Rational[] cords) throws InvalidDimensionsCountException {
        if (cords.length != dimensions) {
            throw new InvalidDimensionsCountException();
        }
        ArrayList<Rational> list = new ArrayList<>();
        for (int i = 0; i < cords.length; i++) {
            list.add(cords[i]);
        }
        this.Dimensions = dimensions;
        this.Cords = list;
    }
    public Point(ArrayList<Rational> cords) {
        this.Dimensions = cords.size();
        this.Cords = cords;
    }
    public Point(Rational[] cords) {
        ArrayList<Rational> list = new ArrayList<>();
        for (int i = 0; i < cords.length; i++) {
            list.add(cords[i]);
        }
        this.Dimensions = cords.length;
        this.Cords = list;
    }
    public boolean Equal(Point input) throws DifferentPointsCordsCountsException {
        if (this.Dimensions != input.Dimensions) {
            throw new DifferentPointsCordsCountsException();
        }
        for (int i = 0; i < input.Dimensions; i++) {
            if (this.Cords.get(i) != input.Cords.get(i)) {
                return false;
            }
        }
        return true;
    }
}
