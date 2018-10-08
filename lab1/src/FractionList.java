import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by alexx on 12.09.2018.
 */

public class FractionList {
    private ArrayList<Fraction> list;
    private Fraction cachedMax;
    private Fraction cachedMin;

    FractionList() {
        list = new ArrayList<Fraction>();
        cachedMax = null;
        cachedMin = null;
    }

    public int size() {
        return list.size();
    }

    public Fraction get(int i) {
        return list.get(i);
    }

    public void add(Fraction frac) {
        list.add(frac);
        cachedMax = null;
        cachedMin = null;
    }

    public Fraction max() {
        if (cachedMax == null) cachedMax = Collections.max(list);
        return cachedMax;
    }

    public Fraction min() {
        if (cachedMin == null) cachedMin = Collections.min(list);
        return cachedMin;
    }

    public int countLargerThan(Fraction x) {
        int count = 0;
        for (Fraction rat: list) {
            if (rat.getNum() * x.getDenom() > x.getNum() * rat.getDenom()) {
                count++;
            }
        }
        return count;
//        return list.stream()
//                .filter(r -> r.compareTo(this) > 0)
//                .count();
    }

    public int countSmallerThan(Fraction x) {
        int count = 0;
        for (Fraction rat: list) {
            if (rat.getNum() * x.getDenom() < x.getNum() * rat.getDenom()) {
                count++;
            }
        }
        return count;
    }

    public void readFile(String fileName) {
        try (Scanner scanner =  new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] str = line.split("/");
                if (str.length != 2) {
                    throw new ArithmeticException("Expected only two arguments");
                }
                int m = Integer.parseInt(str[0]);
                int n = Integer.parseInt(str[1]);
                list.add(new Fraction(m, n));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i)).append(" ");
        }
        return sb.toString();
    }
}
