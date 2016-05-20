package com.globant.bootcamp.finalshopping.repositories;

import com.globant.bootcamp.finalshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product getByName(String name);
    List<Product> findByCategory(Product.Category category);
}
