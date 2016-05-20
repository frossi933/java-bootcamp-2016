package com.globant.bootcamp.shoppingCartRest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;

@Entity
public class Sale {

    @Id @GeneratedValue
    private long id;
    private double total = 0.0;
    private String paymentMethod;
    private LinkedList<Product> products;

    public Sale(Iterable<Product> products, String paymentMethod){
        this.paymentMethod = paymentMethod;
        this.products = new LinkedList<>();
        for(Product p : products){
            this.products.add(p);
            total+=p.getPrice();
        }

    }

    public double getTotal() {
        return total;
    }

    public LinkedList<Product> getProducts() {
        return new LinkedList<>(products);
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
