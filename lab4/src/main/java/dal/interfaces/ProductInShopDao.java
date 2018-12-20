package dal.interfaces;

import entity.ProductInShop;

import java.util.List;

public interface ProductInShopDao extends Dao<ProductInShop, ProductInShop> {

    List<ProductInShop> getByIdShop(int id);

    List<ProductInShop> getByNameProduct(String id);

    /*void deliveryProducts(Shop shop, List<ProductInShop> productInShopList);

    Shop findCheapShop(Product product);

    List<ProductInShop> whatProducts(double money);

    double costListProducts(ProductInShop productInShop);

    Shop findShopWithMinCostOnListProducts(ProductInShop productInShop);*/
}
