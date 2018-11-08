package dao;

import domain.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO {
    void addShop(Shop shop) throws SQLException;

    void addProduct(Product product) throws SQLException;

    void deliveryOfProducts(PriceList priceList) throws SQLException;

    Shop findShopWithMinPrice(Product product) throws SQLException;

    ProductList whatCanBuy(Shop shop, double money) throws SQLException;

    double buySetOfProducts(Shop shop, ProductList productList) throws SQLException;

    Shop findShopWithMinPriceForSetOfProducts(ProductList productList) throws SQLException;
}
