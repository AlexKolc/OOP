package dal.implementations.CSV;

import dal.interfaces.ProductDao;
import utils.CsvUtil;
import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCSVImpl implements ProductDao {
    private String fileName = "src/main/resources/CSV/PriceList.csv";

    @Override
    public void add(Product product) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        boolean haveProduct = false;
        for (String[] item: items) {
            if (product.getName().equals(item[0])) {
                haveProduct = true;
                break;
            }
        }
        if (!haveProduct) {
            items.add(product.toCsvFormat());
            CsvUtil.writeToCsv(fileName, items);
        }
    }

    @Override
    public List<Product> getAll() {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        List<Product> products = new ArrayList<>();
        for (String[] item: items) {
            Product product = new Product();
            product.setName(item[0]);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getById(String id) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        Product product = null;
        for (String[] item: items) {
            if (id.equals(item[0])) {
                product = new Product(id);
                break;
            }
        }
        return product;
    }

    @Override
    public void update(Product obj) {
        System.out.println("Unable to update");
    }

    @Override
    public void delete(Product product) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        for (String[] item: items) {
            if (product.getName().equals(item[0])) {
                items.remove(item);
            }
        }
        CsvUtil.writeToCsv(fileName, items);
    }
}
