package project.service;



import project.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public void saveProduct(Product product);

    public Product getProduct(int id) throws Exception;

    public void deleteProduct(int id);

    public List<Product> getByName(String name);

    public List<Product> findAllByName(String name);
}
