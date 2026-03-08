package MultiDimensions.SecondVersion;

import java.util.*;
import MultiDimensions.SecondVersion.Exceptions.*;
import static MultiDimensions.SecondVersion.Rational.*;

public class Utils {
    public static <Z> ArrayList<ArrayList<Z>> ArrayToList(Z[][] input) {
        ArrayList<ArrayList<Z>> output = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            ArrayList<Z> addition = new ArrayList<>();
            for (int j = 0; j < input[i].length; j++) {
                addition.add(input[i][j]);
            }
            output.add(addition);
        }
        return output;
    }
    // Возвращает входящую таблицу без indexh-того столбца и indexv-той строки.
    public static <Z> ArrayList<ArrayList<Z>> ListWithout(ArrayList<ArrayList<Z>> input, int indexh, int indexv) {
        ArrayList<ArrayList<Z>> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (i == indexv) {
                continue;
            }
            ArrayList<Z> addition = new ArrayList<>();
            for (int j = 0; j < input.size(); j++) {
                if (j == indexh) {
                    continue;
                }
                addition.add(input.get(i).get(j));
            }
            output.add(addition);
        }
        return output;
    }
    // Возвращает входящую таблицу без indexh-того столбца.
    public static <Z> ArrayList<ArrayList<Z>> ListWithout(ArrayList<ArrayList<Z>> input, int indexh) {
        return ListWithout(input, indexh, -1);
    }
    // Возвращает определитель входящей матрицы.
    public static Rational Determinant(ArrayList<ArrayList<Rational>> matrix) throws InvalidMatrixException, DivideByZeroException {
        try {
            int c = matrix.size();
            for (int i = 0; i < c; i++) {
                if (matrix.get(i).size() != c) {
                    throw new InvalidMatrixException();
                }
            }
            if (c == 0) {
                return new Rational(0, 1);
            }
            if (c == 1) {
                return matrix.get(0).get(0);
            }
            if (c == 2) {
                try {
                    return Subtraction(Multiplication(matrix.get(0).get(0), matrix.get(1).get(1)), Multiplication(matrix.get(0).get(1), matrix.get(1).get(0)));
                } catch (Exception ex) {
                    return new Rational(0, 1);
                }
            }
            Rational output = new Rational(0, 1);
            for (int i = 0; i < c; i++) {
                Rational addition = Multiplication(matrix.get(0).get(i), Determinant(ListWithout(matrix, i, 0)));
                if (i % 2 == 0) {
                    output.Addict(addition);
                } else {
                    output.Subtract(addition);
                }
            }
            return output;
        } catch (DivideByZeroException ex) {
            System.out.println("Program broken in Determinant");
            return new Rational(0, 1);
        }
    }
    public static Rational Determinant(Rational[][] matrix) throws InvalidMatrixException, DivideByZeroException {
        return Determinant(ArrayToList(matrix));
    }
    // Возвращает решение к входящей системе уравнений
    public static ArrayList<Rational> Solve(ArrayList<ArrayList<Rational>> system) throws InvalidEquationSystemException, NotSingleSolutionException, DivideByZeroException {
        int c = system.size();
        for (int i = 0; i < c; i++) {
            if (system.get(i).size() != c + 1) {
                throw new InvalidEquationSystemException();
            }
        }
        Rational main = new Rational(0, 1);
        try {
            main = Determinant(ListWithout(system, system.size()));
        } catch (InvalidMatrixException e) { }
        if (main.up == 0) {
            throw new NotSingleSolutionException();
        }
        ArrayList<Rational> output = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            ArrayList<ArrayList<Rational>> part = new ArrayList<>();
            for (int j = 0; j < c; j++) {
                ArrayList<Rational> mini = new ArrayList<>();
                for (int k = 0; k < c; k++) {
                    if (i == k) {
                        mini.add(system.get(j).get(system.size()));
                        continue;
                    }
                    mini.add(system.get(j).get(k));
                }
                part.add(mini);
            }
            try {
                output.add(Division(Determinant(part), main));
            } catch (InvalidMatrixException e) { }
        }
        return output;
    }
    public static ArrayList<Rational> Solve(Rational[][] system) throws InvalidEquationSystemException, NotSingleSolutionException, DivideByZeroException {
        return Solve(ArrayToList(system));
    }
}