package MultiDimensions.SecondVersion;

import MultiDimensions.SecondVersion.Exceptions.DivideByZeroException;

public class Rational {
    public long up;
    public long down;
    public Rational(double up, double down) throws DivideByZeroException {
        if (down == 0) {
            throw new DivideByZeroException();
        }
        // Числитель и знаменатель не предусмотрены для дробных чисел, поэтому умножаем их на 10, пока они не станут целыми.
        while (up != Math.floor(up) || down != Math.floor(down)) {
            up *= 10;
            down *= 10;
        }
        this.up = (long)up;
        this.down = (long)down;
        Rationalize();
    }
    public Rational(long up, long down) throws DivideByZeroException {
        if (down == 0) {
            throw new DivideByZeroException();
        }
        this.up = (long)up;
        this.down = (long)down;
        Rationalize();
    }
    // Упрощение дроби.
    public void Rationalize() {
        if (this.up == 0) {
            this.down = 1;
            return;
        }
        if (this.down < 0) {
            this.down = -this.down;
            this.up = -this.up;
        }
        boolean negative = this.up < 0;
        if (negative) {
            this.up = -this.up;
        }
        if (this.up == this.down) {
            if (negative) {
                this.up = -1;
            } else {
                this.up = 1;
            }
            this.down = 1;
            return;
        }
        for (int i = 2; i <= Math.min(this.up, this.down); i++) {
            while ((this.up % i == 0) && (this.down % i == 0)) {
                this.up /= i;
                this.down /= i;
                if (this.up == 1 || this.down == 1) {
                    if (negative) {
                        this.up = -this.up;
                    }
                    return;
                }
            }
        }
        if (negative) {
            this.up = -this.up;
        }
    }
    public float ToFloat() {
        return (float)(this.up / this.down);
    }
    public double ToDouble() {
        return (double)(this.up / this.down);
    }
    public long ToLong() {
        return (long)(this.up / this.down);
    }
    public void Addict(long input) {
        this.up += this.down * input;
        Rationalize();
    }
    public void Addict(Rational input) {
        this.up = (this.up * input.down) + (input.up * this.down);
        this.down *= input.down;
        Rationalize();
    }
    public void Subtract(long input) {
        this.up -= this.down * input;
        Rationalize();
    }
    public void Subtract(Rational input) {
        this.up = (this.up * input.down) - (input.up * this.down);
        this.down *= input.down;
        Rationalize();
    }
    public void Multiply(long input) {
        this.up *= input;
        Rationalize();
    }
    public void Divide(long input) throws DivideByZeroException {
        if (input == 0) {
            throw new DivideByZeroException();
        }
        this.down *= input;
        Rationalize();
    }
    public boolean Equal(Rational input) {
        if (this.up == input.up && this.down == input.down) {
            return true;
        }
        return false;
    }
    public static Rational Addiction(Rational i1, Rational i2) throws DivideByZeroException {
        return new Rational((i1.up * i2.down) + (i2.up * i1.down), i1.down * i2.down);
    }
    public static Rational Subtraction(Rational i1, Rational i2) throws DivideByZeroException {
        return new Rational((i1.up * i2.down) - (i2.up * i1.down), i1.down * i2.down);
    }
    public static Rational Multiplication(Rational i1, Rational i2) throws DivideByZeroException {
        return new Rational(i1.up * i2.up, i1.down * i2.down);
    }
    public static Rational Division(Rational i1, Rational i2) throws DivideByZeroException {
        return new Rational(i1.up * i2.down, i2.up * i1.down);
    }
    public static boolean Same(Rational i1, Rational i2) {
        if (i1.up == i2.up && i1.down == i2.down) {
            return true;
        }
        return false;
    }
}