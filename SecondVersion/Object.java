package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.*;

import java.util.ArrayList;

public class Object {
    public int Dimensions;
    public ArrayList<Rational> Parameters;
    public Object(int dimensions, ArrayList<Rational> parameters) throws UnsupportedDimensionsCountException {
        if (dimensions < 0) {
            throw new UnsupportedDimensionsCountException();
        }
        this.Dimensions = dimensions;
        this.Parameters = parameters;
    }
    public Object(int dimensions, Rational[] parameters) throws UnsupportedDimensionsCountException {
        if (dimensions < 0) {
            throw new UnsupportedDimensionsCountException();
        }
        ArrayList<Rational> list = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            list.add(parameters[i]);
        }
        this.Dimensions = dimensions;
        this.Parameters = list;
    }
    public Object(ArrayList<ArrayList<Rational>> cords) throws DivideByZeroException, NotSingleObjectException, InvalidDimensionsCountException {
        for (int i = 0; i < cords.size(); i++) {
            cords.get(i).add(new Rational(-1, 1));
        }
        try {
            ArrayList<Rational> parameters = Utils.Solve(cords);
            this.Dimensions = parameters.size();
            parameters.add(new Rational(1, 1));
            this.Parameters = parameters;
        } catch (NotSingleSolutionException e) {
            throw new NotSingleObjectException();
        } catch (InvalidEquationSystemException e) {
            throw new InvalidDimensionsCountException();
        }
    }
}