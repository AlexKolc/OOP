/**
 * Created by alexx on 12.09.2018.
 */
public class Fraction implements Comparable<Fraction>{
    static int nod(int a, int b) {
        while (a != 0 && b != 0) {
            if (a > b)
                a %= b;
            else
                b %= a;
        }
        return a + b;
    }

    private int num;
    private int denom;

    public Fraction(int num, int denom) {
        int sign = 1;
        if (num < 0) {
            sign *= -1;
            num = -num;
        }
        if (denom < 0) {
            sign *= -1;
            denom = -denom;
        }
        this.num = num;
        this.denom = denom;

        int x = nod(this.num, this.denom);
        this.num /= x * sign;
        this.denom /= x;
    }

    public Fraction sum(Fraction x) {
        return new Fraction(num * x.denom + x.num * denom, denom * x.denom);
    }

    public int getNum() {
        return num;
    }

    public int getDenom() {
        return denom;
    }

    @Override
    public String toString() {
        return String.valueOf(num) + "/" + String.valueOf(denom);
    }

    @Override
    public int compareTo(Fraction other) {
        if (num * other.denom > other.num * denom)
            return 1;
        else if (num * other.denom < other.num * denom)
            return -1;
        return 0;
    }
}
