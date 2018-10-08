/**
 * Created by alexx on 26.09.2018.
 */

// Polynomial is a_{0} + a_{1}x^{1} + ... + a_{n-1}x^{n-1} + a_{n}x^{n}

public class Polynomial {
    private FractionList coefficients;

    Polynomial() {
        coefficients = new FractionList();
    }

    Polynomial(FractionList list) {
        coefficients = list;
    }

    public Fraction get(int i) {
        return coefficients.get(i);
    }

    private void add(Fraction x) {
        coefficients.add(x);
    }

    public Polynomial sum(Polynomial x) {
        Polynomial newPol = new Polynomial();
        //newPol.write();
        int begin = Math.min(coefficients.size(), x.coefficients.size());
        int end = Math.max(coefficients.size(), x.coefficients.size());
        for (int i = 0; i < begin; i++) {
            newPol.add(get(i).sum(x.get(i)));
        }
        for (int i = begin; i < end; i++)
            newPol.add(get(i));
        return newPol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coefficients.size(); i++) {
            if (i == 0) {
                sb.append(coefficients.get(i));
                continue;
            }
            if (coefficients.get(i).getNum() > 0)
                sb.append("+");
            sb.append(coefficients.get(i)).append("*x^").append(i);
        }
        return sb.toString();
    }
}
