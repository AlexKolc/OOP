package dal.implementations.CSV;

import dal.interfaces.ProductInShopDao;
import utils.CsvUtil;
import entity.ProductInShop;

import java.util.ArrayList;
import java.util.List;

public class ProductInShopCSVImpl implements ProductInShopDao {
    private String fileName = "src/main/resources/CSV/PriceList.csv";

    @Override
    public List<ProductInShop> getByIdShop(int id) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        List<ProductInShop> productInShops = new ArrayList<>();
        for (String[] item : items) {
            for (int i = 1; i < item.length; i += 3) {
                if (id == Integer.valueOf(item[i])) {
                    ProductInShop productInShop = new ProductInShop();
                    productInShop.setIdShop(Integer.valueOf(item[i]));
                    productInShop.setIdProduct(item[0]);
                    productInShop.setPrice(Double.valueOf(item[i + 2]));
                    productInShop.setCount(Integer.valueOf(item[i + 1]));
                    productInShops.add(productInShop);
                }
            }
        }
        return productInShops;
    }

    @Override
    public List<ProductInShop> getByNameProduct(String product) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        List<ProductInShop> productInShops = new ArrayList<>();
        for (String[] item : items) {
            if (product.equals(item[0])) {
                for (int i = 1; i < item.length; i += 3) {
                    ProductInShop productInShop = new ProductInShop();
                    productInShop.setIdShop(Integer.valueOf(item[i]));
                    productInShop.setIdProduct(item[0]);
                    productInShop.setPrice(Double.valueOf(item[i + 2]));
                    productInShop.setCount(Integer.valueOf(item[i + 1]));
                    productInShops.add(productInShop);
                }
                break;
            }
        }
        return productInShops;
    }

    @Override
    public void add(ProductInShop productInShop) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        boolean haveProduct = false;
        String[] newItem = null;
        for (String[] item : items) {
            if (!productInShop.getIdProduct().equals(item[0]))
                continue;
            haveProduct = true;
            int sz = item.length;
            newItem = new String[sz + 3];
            System.arraycopy(item, 0, newItem, 0, sz);
            newItem[sz] = Integer.toString(productInShop.getIdShop());
            newItem[sz + 1] = Integer.toString(productInShop.getCount());
            newItem[sz + 2] = Double.toString(productInShop.getPrice());
            items.remove(item);
            break;
        }
        if (!haveProduct) {
            newItem = new String[4];
            newItem[0] = productInShop.getIdProduct();
            newItem[1] = Integer.toString(productInShop.getIdShop());
            newItem[2] = Integer.toString(productInShop.getCount());
            newItem[3] = Double.toString(productInShop.getPrice());
        }
        items.add(newItem);
        CsvUtil.writeToCsv(fileName, items);
    }

    @Override
    public List<ProductInShop> getAll() {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        List<ProductInShop> productInShops = new ArrayList<>();

        for (String[] item : items) {
            for (int i = 1; i < item.length; i += 3) {
                ProductInShop productInShop = new ProductInShop();
                productInShop.setIdProduct(item[0]);
                productInShop.setIdShop(Integer.valueOf(item[i]));
                productInShop.setCount(Integer.valueOf(item[i + 1]));
                productInShop.setPrice(Double.valueOf(item[i + 2]));
                productInShops.add(productInShop);
            }
        }
        return productInShops;
    }

    @Override
    public ProductInShop getById(ProductInShop id) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        ProductInShop productInShop = null;
        for (String[] item : items) {
            if (id.getIdProduct().equals(item[0])) {
                for (int i = 1; i < item.length; i += 3) {
                    if (id.getIdShop() == Integer.valueOf(item[i])) {
                        productInShop = new ProductInShop();
                        productInShop.setIdShop(Integer.valueOf(item[i]));
                        productInShop.setIdProduct(item[0]);
                        productInShop.setPrice(Double.valueOf(item[i + 2]));
                        productInShop.setCount(Integer.valueOf(item[i + 1]));
                        break;
                    }
                }
                break;
            }
        }
        return productInShop;
    }

    @Override
    public void update(ProductInShop productInShop) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        for (String[] item : items) {
            if (productInShop.getIdProduct().equals(item[0])) {
                for (int i = 1; i < item.length; i += 3) {
                    if (productInShop.getIdShop() == Integer.valueOf(item[i])) {
                        item[i + 1] = Integer.toString(productInShop.getCount());
                        item[i + 2] = Double.toString(productInShop.getPrice());
                        break;
                    }
                }
                break;
            }
        }
        CsvUtil.writeToCsv(fileName, items);
    }

    @Override
    public void delete(ProductInShop productInShop) {
        List<String[]> items = CsvUtil.readFromCsv(fileName);
        for (String[] item : items) {
            int sz = item.length;
            boolean haveProductInShop = false;
            String[] newItem = new String[sz - 3];
            if (productInShop.getIdProduct().equals(item[0])) {
                for (int i = 1; i < sz; i += 3) {
                    if (productInShop.getIdShop() == Integer.valueOf(item[i])) {
                        haveProductInShop = true;
                        break;
                    }
                }
                if (haveProductInShop) {
                    int j = 1;
                    newItem[0] = item[0];
                    for (int i = 1; i < sz; i += 3) {
                        if (productInShop.getIdShop() != Integer.valueOf(item[i])) {
                            newItem[j++] = item[i];
                            newItem[j++] = item[i + 1];
                            newItem[j++] = item[i + 2];
                        }
                    }
                }
            }
            if (haveProductInShop) {
                items.remove(item);
                items.add(newItem);
            }
        }
        CsvUtil.writeToCsv(fileName, items);
    }
}
