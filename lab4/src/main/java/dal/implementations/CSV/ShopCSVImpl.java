package dal.implementations.CSV;

import dal.interfaces.ShopDao;
import utils.CsvUtil;
import entity.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopCSVImpl implements ShopDao {
    private String fileName = "src/main/resources/CSV/Shops.csv";

    @Override
    public void add(Shop shop) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        Integer id = null;
        for (String[] item : items) {
            id = Integer.valueOf(item[0]);
        }

        if (id == null)
            id = 0;

        shop.setId(id + 1);
        items.add(shop.toCsvFormat());
        CsvUtil.writeToCsv(fileName, items);
    }

    @Override
    public List<Shop> getAll() {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        List<Shop> shops = new ArrayList<>();
        for (String[] item : items) {
            Shop shop = new Shop();
            shop.setId(Integer.valueOf(item[0]));
            shop.setTitle(item[1]);
            shops.add(shop);
        }
        return shops;
    }

    @Override
    public Shop getById(Integer id) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        Shop shop = null;
        boolean haveShop = false;
        for (String[] item : items) {
            if (id == Integer.valueOf(item[0])) {
                shop = new Shop();
                shop.setId(id);
                shop.setTitle(item[1]);
                break;
            }
        }
        return shop;
    }

    @Override
    public void update(Shop shop) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        for (String[] item : items) {
            if (shop.getId() == Integer.valueOf(item[0])) {
                item[1] = shop.getTitle();
            }
        }
        CsvUtil.writeToCsv(fileName, items);
    }

    @Override
    public void delete(Shop shop) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        for (String[] item : items) {
            if (shop.getId() == Integer.valueOf(item[0])) {
                items.remove(item);
            }
        }
        CsvUtil.writeToCsv(fileName, items);
    }
}
