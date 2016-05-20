package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.model.Product;

import java.util.List;

public interface CatalogService {

    boolean addProduct(Product product);
    boolean removeProduct(int id);
    boolean updateProduct(Product product);
    Product getProductById(int id);
    Product getProductByName(String name);
    List<Product> getProductsByCategory(Product.Category category);
    List<Product> getAll();

}
