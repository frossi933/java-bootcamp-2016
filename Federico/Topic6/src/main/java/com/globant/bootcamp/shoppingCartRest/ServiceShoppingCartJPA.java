package com.globant.bootcamp.shoppingCartRest;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceShoppingCartJPA implements ServiceShoppingCart {

    private String paymentMethod;
    private double total = 0;
    private ProductRepository repo;

    @Autowired
    public ServiceShoppingCartJPA(ProductRepository repo){
        this.repo = repo;
    }

    @Override
    public void setPaymentMethod(String method) {
        paymentMethod = method;
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public boolean isEmpty() {
        return (repo.count() == 0);
    }

    @Override
    public boolean addProduct(Product prod) {
        if(!repo.exists(prod.getId()))
            // adding...
            total+=prod.getPrice();

        repo.save(prod);
        return true;
    }

    @Override
    public boolean addMultipleProducts(Product prod, int quantity) {
        // Each product now has a unique id, so this function is not available anymore
        return false;
    }

    @Override
    public boolean rmProduct(long id) {
        if(!repo.exists(id))
            return false;

        total-=repo.findOne(id).getPrice();
        repo.delete(id);
        return true;
    }

    @Override
    public boolean rmMultipleProducts(long id, int quantity) {
        // Each product now has a unique id, so this function is not available anymore
        return false;
    }

    @Override
    public void clearProducts() {
        repo.deleteAll();
        total = 0;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public Iterable<Product> getProductList() {
        return repo.findAll();
    }

    @Override
    public Sale buy() {
        return new Sale(repo.findAll());
    }
}
