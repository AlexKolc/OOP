package core;

import dal.interfaces.ProductDao;
import dal.interfaces.ProductInShopDao;
import dal.interfaces.ShopDao;
import entity.Product;
import entity.ProductInShop;
import entity.Shop;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductService {
    private ShopDao shopDao;
    private ProductDao productDao;
    private ProductInShopDao productInShopDao;

    public ProductService(ShopDao shopDao, ProductDao productDao, ProductInShopDao productInShopDao) {
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productInShopDao = productInShopDao;
    }

    /**
     * Method 2
     * Add new product
     *
     * @param product
     */
    public void addProduct(Product product) {
        try {
            productDao.add(product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method 3
     *
     * @param shop
     * @param hashMap
     */
    public void makeDeliveryProducts(Shop shop,  HashMap<Product, Pair<Double, Integer>> hashMap) {
        try {
            if (!hashMap.isEmpty()) {
                for (Product product : hashMap.keySet()) {
                    ProductInShop productInShop = productInShopDao
                            .getById(new ProductInShop(shop.getId(), product.getName()));
                    if (productInShop == null) {
                        productInShop = new ProductInShop(shop.getId(), product.getName()
                                , hashMap.get(product).getKey(), hashMap.get(product).getValue());
                        productInShopDao.add(productInShop);
                    } else {
                        double newPrice = hashMap.get(product).getKey();
                        int newCount = hashMap.get(product).getValue();
                        productInShop.setCount(productInShop.getCount() + newCount);
                        productInShop.setPrice(newPrice);
                        productInShopDao.update(productInShop);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method 5
     * This method finds what you can buy
     * for a certain amount in this store
     *
     * @param shop
     * @param cash
     * @return products with count
     */
    public HashMap<Product, Integer> findProductsCanBuy(Shop shop, double cash) {
        try {
            ArrayList<ProductInShop> productInShops = new ArrayList<>(productInShopDao.getByIdShop(shop.getId()));
            HashMap<Product, Integer> hashMap = new HashMap<>();
            Product product;
            for (ProductInShop productInShop : productInShops) {
                double price = productInShop.getPrice();
                int count = productInShop.getCount();
                int canBuy = (int) Math.floor(cash / price);
                if (canBuy <= count) {
                    product = productDao.getById(productInShop.getIdProduct());
                    hashMap.put(product, canBuy);
                } else if (count != 0) {
                    product = productDao.getById(productInShop.getIdProduct());
                    hashMap.put(product, count);
                }
            }
            return hashMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Method 6
     *
     * @param shop
     * @param hashMap
     * @return sum order
     */
    public Double buyProducts(Shop shop, HashMap<Product, Integer> hashMap) {
        try {
            if (!hashMap.isEmpty()) {
                double countCash = 0.0;
                for (Product product : hashMap.keySet()) {
                    ProductInShop productInShop = productInShopDao
                            .getById(new ProductInShop(shop.getId(), product.getName()));

                    int neededCount = hashMap.get(product);

                    if (productInShop.getCount() < neededCount) {
                        System.out.println("Not needed count of '" + product.getName() + "'");
                        return null;
                    }
                }

                for (Product product : hashMap.keySet()) {

                    ProductInShop productInShop = productInShopDao
                            .getById(new ProductInShop(shop.getId(), product.getName()));
                    int neededCount = hashMap.get(product);
                    countCash += neededCount * productInShop.getPrice();
                    productInShop.setCount(productInShop.getCount() - neededCount);
                    productInShopDao.update(productInShop);
                }
                return countCash;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

    git filter-branch --force --index-filter \
        'git rm --cached --ignore-unmatch <myfile>' \
        --prune-empty --tag-name-filter cat -- --all
