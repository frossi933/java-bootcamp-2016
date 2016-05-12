package com.globant.bootcamp.shoppingCartRest;

import java.util.List;

public interface ServiceShoppingCart {

    void setPaymentMethod(String method);

    String getPaymentMethod();

    boolean isEmpty();

    boolean addProduct(Product prod);

    boolean addMultipleProducts(Product prod, int quantity);

    boolean rmProduct(long id);

    boolean rmMultipleProducts(long id, int quantity);

    void clearProducts();

    /* do not use double to represent money in real projects */
    double getTotal();

    Iterable<Product> getProductList();

    Sale buy();
}
