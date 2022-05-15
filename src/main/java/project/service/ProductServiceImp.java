package project.service;

import project.dao.ProductRep;
import project.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRep productRep;

    public List<Product> getByName(String name) {
        List<Product> products= productRep.findAll();
        Iterator<Product> productIterator=products.iterator();
        while(productIterator.hasNext()){
            Product product=productIterator.next();
            if(!product.getName().equals(name)){
                productIterator.remove();
            }
        }

        return products;
    }

    @Override
    public List<Product> findAllByName(String name) {
        List<Product> products=productRep.findAllByName(name);
        return products;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRep.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRep.save(product);
    }

    @Override
    public Product getProduct(int id) throws Exception {
        Optional<Product> optional = productRep.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new EntityNotFoundException("Объект не найден");
        }

        return product;
    }

    @Override
    public void deleteProduct(int id) {
        productRep.deleteById(id);
    }


}
