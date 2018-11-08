package dao;

import domain.*;

import java.sql.*;
import java.util.*;

public class SQLImplementation implements DAO {
    private Statement statement;

    SQLImplementation() throws SQLException {
        statement = ConnectionUtil.getConnection().createStatement();
    }

    @Override
    public void addShop(Shop shop) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT Shop(Name, Address) VALUES ('")
                .append(shop.getName())
                .append("', '")
                .append(shop.getAddress())
                .append("')");
        statement.executeUpdate(sb.toString());
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT Product(Name) VALUES ('")
                .append(product.getName())
                .append("')");
        statement.executeUpdate(sb.toString());
    }

    @Override
    public void deliveryOfProducts(PriceList priceList) throws SQLException {
        for (int i = 0; i < priceList.size(); i++) {
            String query = "SELECT * FROM Price WHERE id_price=" + priceList.get(i).getId();
            //System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                query = "SELECT * FROM Product WHERE Name='" + priceList.get(i).getIdProduct() + "'";
                rs = statement.executeQuery(query);
                if (!rs.next())
                    addProduct(new Product(priceList.get(i).getIdProduct()));
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT Price(id_shop, id_product, price, count) VALUES (")
                        .append(priceList.get(i).getIdShop())
                        .append(", '")
                        .append(priceList.get(i).getIdProduct())
                        .append("', ")
                        .append(priceList.get(i).getPrice())
                        .append(", ")
                        .append(priceList.get(i).getCount())
                        .append(")");
                //System.out.println(sb.toString());
                statement.executeUpdate(sb.toString());
            } else {
                query = "UPDATE Price " +
                        "SET price=" + Double.toString(priceList.get(i).getPrice()) +
                        ", count=count+" + Integer.toString(priceList.get(i).getCount()) +
                        " WHERE id_price=" + Integer.toString(priceList.get(i).getId());
                //System.out.println(query);
                statement.executeUpdate(query);
            }
        }
    }

    @Override
    public Shop findShopWithMinPrice(Product product) throws SQLException {
        String query = "SELECT * " +
                "FROM Price " +
                "WHERE price=(" +
                "SELECT MIN(price) FROM Price WHERE id_product='" + product.getName() + "')";
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        query = "SELECT * FROM Shop WHERE id_shop=" + rs.getString("id_shop");
        //System.out.println(query);
        rs = statement.executeQuery(query);
        rs.next();
        return new Shop(rs.getInt("id_shop")
                , rs.getString("Name")
                , rs.getString("Address"));
    }

    @Override
    public ProductList whatCanBuy(Shop shop, double money) throws SQLException {
        ProductList productList = new ProductList();
        String query = "SELECT id_product, price, count " +
                "FROM Price " +
                "WHERE id_shop=" + Integer.toString(shop.getId());
        //System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("id_product");
            double price = rs.getDouble("price");
            int count = rs.getInt("count");
            int canBuy = (int) Math.floor(money / price);
            if (canBuy > 0 && canBuy <= count)
                productList.add(new Product(name), canBuy);
            else if (count > 0 && count < canBuy)
                productList.add(new Product(name), count);
        }
        return productList;
    }

    @Override
    public double buySetOfProducts(Shop shop, ProductList productList) throws SQLException {
        double sum = 0;
        boolean canBuy = true;
        for (int i = 0; i < productList.size(); i++) {
            String query = "SELECT price, count FROM Price WHERE id_shop=" +
                    shop.getId() + " AND id_product='" + productList.get(i).getKey().getName() + "'";
            //System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
                canBuy = false;
                break;
            }
            double price = rs.getDouble("price");
            int count = rs.getInt("count");
            if (count < productList.get(i).getValue()) {
                canBuy = false;
                break;
            }
            sum += productList.get(i).getValue() * price;
        }

        return (canBuy) ? (sum) : (-1);
    }

    @Override
    public Shop findShopWithMinPriceForSetOfProducts(ProductList productList) throws SQLException {
        Map<Integer, Double> prices = new HashMap<>();
        for (int i = 0; i < productList.size(); i++) {
            String query = "SELECT id_shop, price, count FROM Price WHERE id_product='" + productList.get(i).getKey().getName() + "'";
            //System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            Map<Integer, Double> newProduct = new HashMap<>();
            while (rs.next()) {
                int shopID = rs.getInt("id_shop");
                double price = rs.getDouble("price");
                int count = rs.getInt("count");
                if (count < productList.get(i).getValue()) {
                    newProduct.put(shopID, null);
                } else {
                    newProduct.put(shopID, price * productList.get(i).getValue());
                }
            }

            if (i == 0) {
                prices = newProduct;
            } else {
                for(Map.Entry<Integer, Double> entry: prices.entrySet()) {
                    Integer key = entry.getKey();
                    Double value = entry.getValue();
                    if (value != null && newProduct.containsKey(key) && newProduct.get(key) != null) {
                        //map.put(key, map.get(key) + 1);
                        prices.put(key, prices.get(key) + newProduct.get(key));
                    } else {
                        prices.put(key, null);
                    }
                }
            }
        }

        Map.Entry<Integer, Double> minEntry = null;
        for (Map.Entry<Integer, Double> entry : prices.entrySet()) {
            if (minEntry == null || entry.getValue() != null && entry.getValue() < minEntry.getValue()) {
                minEntry = entry;
            }
        }

        String query = "SELECT * FROM Shop WHERE id_shop=" + minEntry.getKey();
        //System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        return new Shop(rs.getInt("id_shop")
                , rs.getString("Name")
                , rs.getString("Address"));
    }

    public static void main(String[] args) throws SQLException {
        SQLImplementation sqlImplementation = new SQLImplementation();// = new SQLImplementation("jdbc:sqlserver://localhost:1433;database=ShopsInformation;integratedSecurity=true");

        sqlImplementation.addShop(new Shop("Дикси", "Кронверский пр. 34"));

        /*PriceList priceList = new PriceList();

        priceList.add(new Price(3, 1, "Шоколад \"Dove\"", 50, 0));
        priceList.add(new Price(6, 2, "Шоколад \"Dove\"", 150, 0));*/
        /*priceList.add(new Price(4, 1, "Напиток \"Fanta\"", 30, 5));
        priceList.add(new Price(5, 1, "Телевизор \"Samsung\"", 15000, 13));*/
        //sqlImplementation.deliveryOfProducts(priceList);
        //System.out.println(sqlImplementation.findShopWithMinPrice(new Product("Крем \"Бархатные ручки\"")));
        Shop shop = new Shop(1);
        //System.out.println(sqlImplementation.whatCanBuy(shop, 100));
        ProductList productList = new ProductList();
        //productList.add(new Product("Шоколад \"Dove\""), 2);
        productList.add(new Product("Шоколад \"Dove\""), 1);
        productList.add(new Product("Крем \"Бархатные ручки\""), 3);

        System.out.println(sqlImplementation.findShopWithMinPriceForSetOfProducts(productList));
    }
}
