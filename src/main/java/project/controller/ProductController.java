package project.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.errors.NullPointerException;
import project.entity.Ingridient;
import project.entity.Product;
import project.errors.PersistenceException;
import project.service.IngridientService;
import project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private ProductService productService;

    @Autowired
    private IngridientService ingridientService;

    @CrossOrigin
    @PostMapping("/ingNew/{weight}")
    public Ingridient newIngridientProduct(@PathVariable int weight, @RequestBody Product product) throws Exception {
        logger.info("Creation Ingridient" +product.getName()+" "+weight);
        Product newProduct = productService.getProduct(product.getId());
        Ingridient ingridient = new Ingridient();
        ingridient.setProduct(newProduct);
        ingridient.setWeight(weight);
        ingridientService.saveIngridient(ingridient);
        return ingridient;
    }

    @CrossOrigin
    @GetMapping("")
    public List<Product> showAllProducts() {

        List<Product> products = productService.getAllProducts();
        return products;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) throws Exception {
        Product product = productService.getProduct(id);
        return product;
    }

    @CrossOrigin
    @PostMapping("")
    public Product addNewProduct(@RequestBody Product product) {
        if ( product.getName() == null || product.getName().isEmpty()) {
            logger.error("Введено пустое имя");
            throw new NullPointerException("Введено пустое имя");
        }
        if ( product.getName().length() > 30) {
            logger.error("Слишком большое имя продукта");
            throw new PersistenceException("Слишком большое имя продукта");
        }
        productService.saveProduct(product);
        return product;
    }


    @CrossOrigin
    @GetMapping("/name/{name}")
    public Product getByName(@PathVariable("name") String name) throws Exception {
        logger.info("Attempt to get product or create with name= " + name);
        if (name == null || name.isEmpty()) {
            logger.error("Введено пустое имя");
            throw new NullPointerException("Введено пустое имя");
        }
        if (name.length() > 30) {
            logger.error("Слишком большое имя продукта");
            throw new PersistenceException("Слишком большое имя продукта");
        }
        List<Product> recipes = productService.findAllByName(name);

        if (recipes.size() == 0) {
            Product product = new Product();
            product.setName(name);
            productService.saveProduct(product);

            return product;
        }
        logger.info("Attempt to get product or create with name= " + name+" was succeful");
        return recipes.get(0);
    }


    @CrossOrigin
    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product) {
        if (product.getName().isEmpty()||product.getName()==null) {
            throw new NullPointerException("Пустое имя");
        }
        if (product.getName().length() > 30) {
            logger.error("Слишком большое имя продукта");
            throw new PersistenceException("Слишком большое имя продукта");
        }
        productService.saveProduct(product);
        return product;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }


    @PostMapping("/{id}/{weight}")
    public Ingridient addIngridient(@PathVariable int id, @PathVariable int weight) throws Exception {
        Product product = productService.getProduct(id);
        Ingridient ingridient = new Ingridient(weight);
        product.addIngridient(ingridient);
        productService.saveProduct(product);
        return ingridient;
    }


    @GetMapping("/Test/name/{name}")
    public List<Product> find(@PathVariable String name) {

        List<Product> list = productService.findAllByName(name);
        return list;
    }


}
