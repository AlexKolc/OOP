package core;

import dal.interfaces.ProductDao;
import dal.interfaces.ProductInShopDao;
import dal.interfaces.ShopDao;
import entity.Product;
import entity.ProductInShop;
import entity.Shop;

import java.util.HashMap;
import java.util.List;

public class ShopService {
    private ShopDao shopDao;
    private ProductDao productDao;
    private ProductInShopDao productInShopDao;

    public ShopService(ShopDao shopDao, ProductDao productDao, ProductInShopDao productInShopDao) {
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productInShopDao = productInShopDao;
    }

    /**
     * Method 1
     * Add new shop
     *
     * @param shop
     */
    public void addShop(Shop shop) {
        try {
            shopDao.add(shop);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method 4
     * This method finds a shop that sells
     * the right product with the lowest price.
     *
     * @param product
     * @return shop
     */
    public Shop findCheapestShop(Product product) {
        try {
            List<ProductInShop> productInShops = productInShopDao.getByNameProduct(product.getName());
            double minPrice = productInShops.get(0).getPrice();
            int minPriceIdShop = productInShops.get(0).getIdShop();
            for (ProductInShop productInShop : productInShops) {
                if (productInShop.getPrice() < minPrice) {
                    minPrice = productInShop.getPrice();
                    minPriceIdShop = productInShop.getIdShop();
                }
            }
            return shopDao.getById(minPriceIdShop);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Method 7
     *
     * @param hashMap
     * @return shop where can buy needed products
     * @throws Exception
     */
    public Shop findShopWhereBuy(HashMap<Product, Integer> hashMap) {
        try {
            HashMap<Shop, Double> shopMap = new HashMap<>();
            double resultCash;

            if (!hashMap.isEmpty()) {
                for (Shop shop : shopDao.getAll()) {
                    resultCash = 0;
                    for (Product product : hashMap.keySet()) {
                        try {
                            ProductInShop productInShop = productInShopDao
                                    .getById(new ProductInShop(shop.getId(), product.getName()));
                            if (productInShop.getCount() < hashMap.get(product)) {
                                break;
                            }
                            resultCash += productInShop.getCount() * hashMap.get(product);
                        } catch (Exception ex) {
                            resultCash = 0;
                            break;
                        }
                    }
                    if (resultCash != 0) {
                        shopMap.put(shop, resultCash);
                    }
                }
                if (hashMap.isEmpty())
                    throw new Exception("No such shop");

                double minCash = 0;
                Shop minShop = new Shop();
                for (Shop shop : shopMap.keySet()) {
                    if (shopMap.get(shop) < minCash || minCash == 0) {
                        minCash = shopMap.get(shop);
                        minShop = shop;
                    }
                }
                return minShop;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
