package sevice;

import Model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void save(Product product);
    int findIndexProductName(int id);
    int findIndexById(int id);
    Product findById(int id);
    Product findByName(String name);
    void update(int id, Product product);
    void delete(int id);
}
