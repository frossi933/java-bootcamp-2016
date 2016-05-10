package com.globant.bootcamp.shoppingCartRest;

import java.util.List;

public interface ServiceCatalog {

    boolean newProduct(String id, Product prod);
    boolean deleteProduct(String id);
    boolean updateProduct(String id, Product newProd);
    Product getProduct(String id);
    List<Product> getAllProducts();

}
