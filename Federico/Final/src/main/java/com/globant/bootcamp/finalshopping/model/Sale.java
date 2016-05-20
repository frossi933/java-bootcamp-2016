package com.globant.bootcamp.finalshopping.model;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
public class Sale {

    public enum Mode {
        CASH, CREDIT_CARD, PAYPAL;
    }

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LinkedList<Product> products;
    private Mode mode;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;
    private long total;

    public Sale(){}

    public Sale(Cart cart, Mode mode){
        this.user = cart.getUser();
        this.mode = mode;
        this.date = DateTime.now();
        this.products = new LinkedList<>(cart.getItems());
        this.total = cart.getTotal();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
