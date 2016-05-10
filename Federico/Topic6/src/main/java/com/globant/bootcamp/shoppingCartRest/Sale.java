package com.globant.bootcamp.shoppingCartRest;

import java.util.LinkedList;

public class Sale {

    private double total = 0.0;
    private LinkedList<Product> products;

    public Sale(LinkedList<Product> products){
        this.products = products;
        for(Product p : products)
            total+=p.getPrice();
    }

    public double getTotal() {
        return total;
    }

    public LinkedList<Product> getProducts() {
        return new LinkedList<Product>(products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;

        Sale sale = (Sale) o;

        if (Double.compare(sale.getTotal(), getTotal()) != 0) return false;
        return getProducts() != null ? getProducts().equals(sale.getProducts()) : sale.getProducts() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getTotal());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "total=" + total +
                ", products=" + products +
                '}';
    }
}
