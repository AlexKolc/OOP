package dal.implementations.SQL;

import dal.interfaces.ShopDao;
import utils.ConnectionUtil;
import entity.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopSQLImpl implements ShopDao {

    @Override
    public void add(Shop shop) {
        String query = "INSERT INTO Shops(title, address) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, shop.getTitle());
            preparedStatement.setString(2, shop.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Shop> getAll() {
        String query = "SELECT * FROM Shops";
        List<Shop> shops = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Shop shop = new Shop();
                try {
                    shop.setId(rs.getInt("codeShop"));
                    shop.setTitle(rs.getString("title"));
                    shop.setAddress(rs.getString("address"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                shops.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shops;
    }

    @Override
    public Shop getById(Integer id) {
        String query = "SELECT * FROM Shops WHERE codeShop = ?";
        Shop shop = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt("codeShop"));
                shop.setTitle(rs.getString("title"));
                shop.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return shop;
    }

    @Override
    public void update(Shop shop) {
        String query = "UPDATE Shops SET title = ?, address = ? WHERE codeShop = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, shop.getTitle());
            preparedStatement.setString(2, shop.getAddress());
            preparedStatement.setInt(3, shop.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Shop shop) {
        String query = "DELETE FROM Shops WHERE codeShop = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, shop.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
