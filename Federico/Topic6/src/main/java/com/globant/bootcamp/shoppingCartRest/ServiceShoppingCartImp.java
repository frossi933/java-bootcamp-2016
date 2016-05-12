package com.globant.bootcamp.shoppingCartRest;

import java.util.LinkedList;
import java.util.List;

public class ServiceShoppingCartImp implements ServiceShoppingCart {

    private String paymentMethod;
    private LinkedList<Product> products;
    private double amount = 0.0;

    public ServiceShoppingCartImp(){
        products = new LinkedList<Product>();
    }

    public void setPaymentMethod(String method) {
        paymentMethod = method;
    }

    public String getPaymentMethod() { return paymentMethod; }

    public boolean isEmpty(){ return products.isEmpty(); }

    public boolean addProduct(Product prod) {
        if(prod != null && products.add(prod)) {
            amount += prod.getPrice();
            return true;
        }

        return false;
    }

    public boolean addMultipleProducts(Product prod, int quantity) {
        if(quantity<=0 || prod == null)
            return false;

        for(int i=1;i<=quantity;i++)
            if(!products.add(prod)){
                /* Remove previously added products */
                rmMultipleProducts(prod.getId(),i-1);
                return false;
            }

        amount += (prod.getPrice() * quantity);
        return true;
    }

    public boolean rmProduct(long id) {
        for(Product p : products){
            if(p.getId() == id)
                if(products.remove(p)) {
                    amount -= p.getPrice();
                    return true;
                } else
                    break;
        }
        return false;
    }

    /**
     *
     * @param id
     * @param quantity
     * @return true if "quantity" items (identified by "id") were removed from the list.
     *         false otherwise (maybe some of them were removed)
     */
    public boolean rmMultipleProducts(long id, int quantity) {
        if(quantity<=0)
            return false;

        int deleted = 0;
        for(Product p : products) {

            if (p.getId() == id){
                if(products.remove(p)) {
                    amount -= p.getPrice();
                    deleted++;
                } else
                    return false;
            }

            if(quantity == deleted)
                return true;
        }

        return false;
    }

    public void clearProducts() {
        products.clear();
    }

    public double getTotal() {
        return amount;
    }

    public Iterable<Product> getProductList() {
        return new LinkedList<Product>(products);
    }

    public Sale buy() {
        return new Sale(products);
    }
}
