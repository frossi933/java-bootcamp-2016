package com.globant.bootcamp.api;

import java.util.LinkedList;

public interface ServiceCatalog {

    boolean newProduct(String id, Product prod);
    boolean deleteProduct(String id);
    boolean updateProduct(String id, Product newProd);
    Product getProduct(String id);
    LinkedList<Product> getAllProducts();

}
