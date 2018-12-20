package entity;

public class ProductInShop {
    private int idShop;
    private String idProduct;
    private double price;
    private int count;

    public ProductInShop(int idShop, String idProduct) {
        this.idShop = idShop;
        this.idProduct = idProduct;
    }

    public ProductInShop(int idShop, String idProduct, double price, int count) {
        this.idShop = idShop;
        this.idProduct = idProduct;
        this.price = price;
        this.count = count;
    }

    public ProductInShop() {
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductInShop{" +
                "idShop=" + idShop +
                ", idProduct='" + idProduct + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
