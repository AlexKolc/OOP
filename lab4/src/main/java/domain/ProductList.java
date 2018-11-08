package domain;


import javafx.util.Pair;

import java.util.*;

public class ProductList {
    private static List<Pair<Product, Integer>> productList;

    static {
        productList = new ArrayList<>();
    }

    public void add(Product product, int count) {
        productList.add(new Pair<>(product, count));
    }

    public Pair<Product, Integer> get(int i) {
        return productList.get(i);
    }

    public int size() {
        return productList.size();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(productList.get(i).getKey().getName()).append(" can buy ").append(productList.get(i).getValue()).append("\n");
        }
        return sb.toString();
    }
}
