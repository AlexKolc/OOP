package domain;

import java.util.Objects;

public class Price {
    private int id;
    private double price;
    private int count;
    private int shopID;
    private String productID;

    public Price(final int id, final int shopID, final String productID, final double price, final int count) {
        this.id = id;
        this.shopID = shopID;
        this.productID = productID;
        this.price = price;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIdShop() {
        return shopID;
    }

    public void setIdShop(int shopID) {
        this.shopID = shopID;
    }

    public String getIdProduct() {
        return productID;
    }

    public void setIdProduct(String productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return id == price.id &&
                Double.compare(price.price, this.price) == 0 &&
                count == price.count &&
                shopID == price.shopID &&
                Objects.equals(productID, price.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, count, shopID, productID);
    }


}
