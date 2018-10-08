public class Main {
    public static void main(String[] args) {
        FractionList list1 = new FractionList();
        list1.readFile("input.txt");
        System.out.println("list1: " + list1);
        System.out.println("Max = " + list1.max());
        System.out.println("Min = " + list1.min());
        System.out.println("Count rationals smaller than 1/1 = " +  list1.countSmallerThan(new Fraction(1, 1)));
        System.out.println("Count rationals larger than 1/1 = " +  list1.countLargerThan(new Fraction(1, 1)));

        FractionList list2 = new FractionList();
        list2.readFile("input2.txt");
        Polynomial pol1 = new Polynomial(list1);
        System.out.println("Polynomial 1: " + pol1);
        Polynomial pol2 = new Polynomial(list2);
        System.out.println("Polynomial 2: " + pol2);
        Polynomial pol3 = pol1.sum(pol2);
        System.out.println("Polynomial 1 + Polynomial 2: " + pol3);
    }
}
