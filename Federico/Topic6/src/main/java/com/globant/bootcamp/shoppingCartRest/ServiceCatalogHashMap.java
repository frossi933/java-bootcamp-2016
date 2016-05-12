package com.globant.bootcamp.shoppingCartRest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ServiceCatalogHashMap implements ServiceCatalog {

    private HashMap<Long, Product> catalog;

    public ServiceCatalogHashMap(){
        catalog = new HashMap<Long, Product>();
    }

    public boolean newProduct(long id, Product prod) {
        return (catalog.putIfAbsent(id, prod) == null);
    }

    public boolean deleteProduct(long id) {
        return (catalog.remove(id) != null);
    }

    public boolean updateProduct(long id, Product newProd) {
        return (catalog.replace(id, newProd) != null);
    }

    public Product getProduct(long id) {
        return catalog.get(id);
    }

    public Iterable<Product> getAllProducts() {
        return new LinkedList<Product>(catalog.values());
    }
}
