package dal.implementations.SQL;

import dal.interfaces.ProductDao;
import utils.ConnectionUtil;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSQLImpl implements ProductDao {
    @Override
    public void add(Product product) {
        String query = "INSERT INTO Products(name) VALUES(?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM Products";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Product product = new Product();
                try {
                    product.setName(rs.getString("name"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(String id) {
        String query = "SELECT * FROM Products WHERE name = ?";
        Product product = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(Product product) {
        System.out.println("Unable to update");
    }

    @Override
    public void delete(Product product) {
        String query = "DELETE FROM Products WHERE name = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
