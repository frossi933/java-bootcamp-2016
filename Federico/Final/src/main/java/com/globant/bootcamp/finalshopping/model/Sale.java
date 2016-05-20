package com.globant.bootcamp.finalshopping.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@ApiModel(value = "Sale entity", description = "Represents a final purchase")
public class Sale {

    public enum Mode {
        CASH, CREDIT_CARD, PAYPAL;
    }

    @Id @GeneratedValue
    @ApiModelProperty(value = "Sale identifier")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty(value = "Purchaser user", required = true)
    private User user;
    @ApiModelProperty(value = "Products purchased", required = true)
    private LinkedList<Product> products;
    @ApiModelProperty(value = "Payment method", required = true)
    private Mode mode;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @ApiModelProperty(value = "Date of purchase", required = true)
    private DateTime date;
    @ApiModelProperty(value = "Total cost", required = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;

        Sale sale = (Sale) o;

        return getId() == sale.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                ", mode=" + mode +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
