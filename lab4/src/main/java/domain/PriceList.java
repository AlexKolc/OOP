package domain;

import java.util.*;

public class PriceList {
    private List<Price> priceList;

    public PriceList() {
        priceList = new ArrayList<>();
    }

    public void add(Price price) {
        priceList.add(price);
    }

    public Price get(int i) {
        return priceList.get(i);
    }

    public int size() {
        return priceList.size();
    }
}
