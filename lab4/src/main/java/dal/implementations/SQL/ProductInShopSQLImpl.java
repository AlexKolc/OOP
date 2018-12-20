package dal.implementations.SQL;

import dal.interfaces.ProductInShopDao;
import utils.ConnectionUtil;
import entity.ProductInShop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductInShopSQLImpl implements ProductInShopDao {
    @Override
    public void add(ProductInShop productInShop) {
        String query = "INSERT INTO PriceList(idShop, idProduct, price, [count]) " +
                "VALUES(?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productInShop.getIdShop());
            preparedStatement.setString(2, productInShop.getIdProduct());
            preparedStatement.setDouble(3, productInShop.getPrice());
            preparedStatement.setInt(4, productInShop.getCount());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductInShop> getAll() {
        String query = "SELECT * FROM PriceList";
        List<ProductInShop> productInShops = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ProductInShop productInShop = new ProductInShop();
                try {
                    productInShop.setIdShop(rs.getInt("idShop"));
                    productInShop.setIdProduct(rs.getString("idProduct"));
                    productInShop.setPrice(rs.getDouble("price"));
                    productInShop.setCount(rs.getInt("count"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                productInShops.add(productInShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productInShops;
    }

    @Override
    public ProductInShop getById(ProductInShop id) {
        String query = "SELECT * FROM PriceList WHERE idShop = ? AND idProduct = ?";
        ProductInShop productInShop = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id.getIdShop());
            preparedStatement.setString(2, id.getIdProduct());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productInShop = new ProductInShop();
                productInShop.setIdShop(rs.getInt("idShop"));
                productInShop.setIdProduct(rs.getString("idProduct"));
                productInShop.setPrice(rs.getDouble("price"));
                productInShop.setCount(rs.getInt("count"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productInShop;
    }

    @Override
    public void update(ProductInShop productInShop) {
        String query = "UPDATE PriceList SET price = ?, count = ? " +
                "WHERE idShop = ? AND idProduct = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, productInShop.getPrice());
            preparedStatement.setInt(2, productInShop.getCount());
            preparedStatement.setInt(3, productInShop.getIdShop());
            preparedStatement.setString(4, productInShop.getIdProduct());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(ProductInShop productInShop) {
        String query = "DELETE FROM PriceList WHERE idShop = ? AND idProduct = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productInShop.getIdShop());
            preparedStatement.setString(2, productInShop.getIdProduct());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductInShop> getByIdShop(int id) {
        String query = "SELECT * FROM PriceList WHERE idShop = ?";
        List<ProductInShop> productInShops = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductInShop productInShop = new ProductInShop();
                try {
                    productInShop.setIdShop(rs.getInt("idShop"));
                    productInShop.setIdProduct(rs.getString("idProduct"));
                    productInShop.setPrice(rs.getDouble("price"));
                    productInShop.setCount(rs.getInt("count"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                productInShops.add(productInShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productInShops;
    }

    @Override
    public List<ProductInShop> getByNameProduct(String id) {
        String query = "SELECT * FROM PriceList WHERE idProduct = ?";
        List<ProductInShop> productInShops = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductInShop productInShop = new ProductInShop();
                productInShop.setIdShop(rs.getInt("idShop"));
                productInShop.setIdProduct(rs.getString("idProduct"));
                productInShop.setPrice(rs.getDouble("price"));
                productInShop.setCount(rs.getInt("count"));
                productInShops.add(productInShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productInShops;
    }
}
