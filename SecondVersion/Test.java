package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.*;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        try {
            Space mask = new Space(3);
            Rational[][] points0 = new Rational[][]{
                    { new Rational(-4, 1), new Rational(-1, 1), new Rational(0, 1) },
                    { new Rational(-2, 1), new Rational(-1, 1), new Rational(-4, 1) },
                    { new Rational(0, 1), new Rational(2, 1), new Rational(1, 1) },
            };
            Point[] points1 = new Point[] {
                    new Point(new Rational[] { new Rational(-4, 1), new Rational(1, 1), new Rational(0, 1) }),
                    new Point(new Rational[] { new Rational(-2, 1), new Rational(0, 1), new Rational(-4, 1) }),
                    new Point(new Rational[] { new Rational(0, 1), new Rational(0, 1), new Rational(-8, 1) }),
            };
            Object line0 = new Object(2, new Rational[] {new Rational(2, 1), new Rational(1, 1), new Rational(4, 1)});
            Object line1 = new Object(2, new Rational[] {new Rational(6, 1), new Rational(3, 1), new Rational(11, 1)});
            System.out.println(Space.SameObjects(line0, line1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}