package com.globant.bootcamp.finalshopping.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@ApiModel(value="Cart entity",
        description = "Cart entity associated with an user. It contains a list with products added by the user")
public class Cart {

    @Id @GeneratedValue
    @ApiModelProperty(value = "Cart identifier")
    int idCart;
    @ApiModelProperty(value = "Total cost of all products")
    long total;

    @OneToOne @JoinColumn(name = "id")
    @ApiModelProperty(value = "The owner of the cart", required = true)
    User user;

    @ApiModelProperty(value = "The list of products added by the user", required = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        return idCart == cart.idCart;

    }

    @Override
    public int hashCode() {
        return idCart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", total=" + total +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
