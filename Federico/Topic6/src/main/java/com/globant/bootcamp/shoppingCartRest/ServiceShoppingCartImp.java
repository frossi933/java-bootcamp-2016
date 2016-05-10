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
                rmMultipleProducts(prod,i-1);
                return false;
            }

        amount += (prod.getPrice() * quantity);
        return true;
    }

    public boolean rmProduct(Product prod) {
        if(prod != null && products.remove(prod)){
            amount -= prod.getPrice();
            return true;
        }

        return false;
    }

    public boolean rmMultipleProducts(Product prod, int quantity) {
        if(quantity<=0 || prod == null)
            return false;

        for(int i=1;i<=quantity;i++)
            if(!products.remove(prod)){
                /* Add previously removed products */
                addMultipleProducts(prod,i-1);
                return false;
            }

        amount -= (prod.getPrice() * quantity);
        return true;
    }

    public void clearProducts() {
        products.clear();
    }

    public double getTotal() {
        return amount;
    }

    public List<Product> getProductList() {
        return new LinkedList<Product>(products);
    }

    public Sale buy() {
        return new Sale(products);
    }
}
