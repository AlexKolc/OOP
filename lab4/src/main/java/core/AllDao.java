package core;

import dal.implementations.CSV.ProductCSVImpl;
import dal.implementations.CSV.ProductInShopCSVImpl;
import dal.implementations.CSV.ShopCSVImpl;
import dal.implementations.SQL.ProductInShopSQLImpl;
import dal.implementations.SQL.ProductSQLImpl;
import dal.implementations.SQL.ShopSQLImpl;
import dal.interfaces.ProductDao;
import dal.interfaces.ProductInShopDao;
import dal.interfaces.ShopDao;

import java.io.IOException;
import java.util.Properties;

public class AllDao {
    private ShopDao shopDao;
    private ProductDao productDao;
    private ProductInShopDao productInShopDao;

    public AllDao() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/Properties/config.properties"));
            String type = properties.getProperty("type");
            try {
                if (type.equals("sql")) {
                    shopDao = new ShopSQLImpl();
                    productDao = new ProductSQLImpl();
                    productInShopDao = new ProductInShopSQLImpl();
                } else if (type.equals("csv")) {
                    shopDao = new ShopCSVImpl();
                    productDao = new ProductCSVImpl();
                    productInShopDao = new ProductInShopCSVImpl();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ShopDao getShopDao() {
        return shopDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductInShopDao getProductInShopDao() {
        return productInShopDao;
    }
}
