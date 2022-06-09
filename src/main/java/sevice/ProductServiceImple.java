package sevice;

import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImple implements ProductService {
    List<Product> products;

    public ProductServiceImple() {
        products = new ArrayList<>();
        products.add(new Product(1, "House", 300));
        products.add(new Product(2, "Tree", 500));
        products.add(new Product(3, "Chair", 800));
        products.add(new Product(4, "Door", 300));
        products.add(new Product(5, "Bottle", 900));
        products.add(new Product(6, "Phone", 600));

    }

    @Override
    public List<Product> findAll() {
        return products;
    }
    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public int findIndexProductName(int id) {
        return 0;
    }
    @Override
    public int findIndexById(int id) {
        int index = -1;
        for(int i = 0; i< products.size(); i++){
            if (products.get(i).getId()==id){
                index = i;
            }
        }
        return index;
    }
    @Override
    public Product findById(int id) {
        for(Product product : products){
            if (product.getId() == id) return product;
        }
        return null;
    }
    @Override
    public Product findByName(String name) {
        for(Product product : products){
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    @Override
    public void update(int id, Product product) {
        int indexOf = findIndexById(id);
        products.set(indexOf, product);
    }

    @Override
    public void delete(int id) {

    }
}
