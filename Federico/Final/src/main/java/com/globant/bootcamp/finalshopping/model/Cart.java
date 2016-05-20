package com.globant.bootcamp.finalshopping.model;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Cart {

    @Id @GeneratedValue
    int idCart;
    long total;

    @OneToOne @JoinColumn(name = "id")
    User user;

    //@ManyToMany(fetch = FetchType.EAGER)
    LinkedList<Product> items;

    public Cart(){}

    public Cart(User user){
        this.user = user;
        total = 0;
        items = new LinkedList<>();
    }

    public int getId() {
        return idCart;
    }

    public long getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = new LinkedList<>(items);
        total = 0;
        for(Product p : items)
            this.total += p.getPrice();
    }

    public boolean addItem(Product product){
        if(items.add(product)) {
            total += product.getPrice();
            return true;
        }

        return false;
    }

    public boolean removeItem(Product product){

        if(items.remove(product)){
            total-=product.getPrice();
            return true;
        }

        return false;
    }
}
