package project.dao;


import project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRep extends JpaRepository<Product, Integer> {
    public List<Product> findAllByName(String name);
}
