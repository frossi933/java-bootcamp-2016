package com.globant.bootcamp.shoppingCartRest;


public interface ServiceCatalog {

    boolean newProduct(long id, Product prod);
    boolean deleteProduct(long id);
    boolean updateProduct(long id, Product newProd);
    Product getProduct(long id);
    Iterable<Product> getAllProducts();

}
