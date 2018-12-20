import core.ProductService;
import core.ShopService;
import entity.Product;
import entity.ProductInShop;
import entity.Shop;
import core.AllDao;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop( 3, "Свой", "Красноармейская 12");

        Product product = new Product( "Coca-cola");

        ProductInShop availability = new ProductInShop( 3, "Coca-Cola", 36.76, 100);

        /*Service.addShop(shop);
        Service.addProduct(product);
        Service.addProductInShop(availability);*/

        //HashMap<Product, Pair<Double, Integer>> delivery = new HashMap<>();
        //delivery.put(product, new Pair<>(40.00, 15));
        //delivery.put(new Product("Телевизор PHILIPS"), new Pair<>(25999.0, 14));
        //Service.makeDelivery(shop, delivery);

        AllDao daoUtil = new AllDao();
        ProductService productService = new ProductService(daoUtil.getShopDao()
                , daoUtil.getProductDao(), daoUtil.getProductInShopDao());
        ShopService shopService = new ShopService(daoUtil.getShopDao()
                , daoUtil.getProductDao(), daoUtil.getProductInShopDao());

        System.out.println("The cheapest shop: " + shopService.findCheapestShop(new Product("Телевизор PHILIPS")));
        System.out.println();

        //Service.addProductInShop(new ProductInShop(2, "Coca-Cola", 46.75, 179));

        HashMap<Product, Integer> whatBuy = productService.findProductsCanBuy(shop, 26000.0);
        for (Product product1: whatBuy.keySet()) {
            System.out.println(product + ": " + whatBuy.get(product1));
        }
        System.out.println();

        HashMap<Product, Integer> order = new HashMap<>();
        order.put(new Product("Телевизор PHILIPS"), 1);
        order.put(new Product("Coca-Cola"), 15);
        System.out.println("Summa on order:" + productService.buyProducts(shop, order));
        System.out.println();

        //order.put(new Product("unknown"), 3);
        //System.out.println("Summa on order:" + Service.makeOrder(shop, order));
        //System.out.println();

        System.out.println("Where buy list products: " + shopService.findShopWhereBuy(order));

    }
}
