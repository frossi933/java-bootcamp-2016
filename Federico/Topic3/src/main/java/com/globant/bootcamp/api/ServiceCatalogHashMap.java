package com.globant.bootcamp.api;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ServiceCatalogHashMap implements ServiceCatalog {

    private HashMap<String, Product> catalog;

    public ServiceCatalogHashMap(){
        catalog = new HashMap<String, Product>();
    }

    public boolean newProduct(String id, Product prod) {
        return (catalog.putIfAbsent(id, prod) == null);
    }

    public boolean deleteProduct(String id) {
        return (catalog.remove(id) != null);
    }

    public boolean updateProduct(String id, Product newProd) {
        return (catalog.replace(id, newProd) != null);
    }

    public Product getProduct(String id) {
        return catalog.get(id);
    }

    public LinkedList<Product> getAllProducts() {
        return new LinkedList<Product>(catalog.values());
    }
}
