package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.DifferentPointsCordsCountsException;
import MultiDimensions.SecondVersion.Exceptions.DivideByZeroException;
import MultiDimensions.SecondVersion.Exceptions.InvalidMatrixException;
import MultiDimensions.SecondVersion.Exceptions.NotEnoughPointsException;

import java.util.ArrayList;
import java.util.List;

public class Prototypes {
    public static double Prototype(double[][] matrix) throws InvalidMatrixException {
        int c = matrix.length;
        for (int i = 0; i < c; i++) {
            if (matrix[i].length != c) {
                throw new InvalidMatrixException();
            }
        }
        if (c == 0) {
            return 0;
        }
        if (c == 1) {
            return matrix[0][0];
        }
        if (c == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }
        double output = 0;
        for (int i = 0; i < c; i++) {
            // Govnocode
            List<Double[]> part = List.of();
            try {
                for (int j = 1; j < c; j++) {
                    List<Double> mini = List.of();
                    for (int k = 0; k < c; k++) {
                        if (i == k) {
                            k++;
                        }
                        mini.add(matrix[j][k]);
                    }
                    part.add((Double[])mini.toArray());
                }
            } catch (Exception ex) {
                System.out.println("S");
            }
            double addition = matrix[0][i] * Prototype((double[][])part.toArray());
            if (i % 2 == 0) {
                output += addition;
            } else {
                output -= addition;
            }
            //
        }
        return output;
    }
    public static <Z> Z[][] ListToArray(ArrayList<ArrayList<Z>> input) {
        ArrayList<Z[]> output = new ArrayList<>();
        for (int i = 0; i < output.size(); i++) {
            output.add((Z[])input.get(i).toArray());
        }
        return (Z[][])output.toArray();
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
                    if (Rational.Same(points[i].Cords.get(j), points[i + 1].Cords.get(j))) {
                        for (int k = i + 1; k < points.length - 1; k++) {
                            if (!Rational.Same(points[k].Cords.get(j), points[k + 1].Cords.get(j))) {
                                return false;
                            }
                        }
                        break;
                    }
                    else if (!Rational.Same(Rational.Division(differences[j], Rational.Subtraction(points[i].Cords.get(j), points[i + 1].Cords.get(j))),
                            Rational.Division(differences[j + 1], Rational.Subtraction(points[i].Cords.get(j + 1), points[i + 1].Cords.get(j + 1))))) {
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
