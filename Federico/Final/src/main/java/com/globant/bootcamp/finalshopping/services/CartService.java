package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.model.Cart;
import com.globant.bootcamp.finalshopping.model.Sale;
import com.globant.bootcamp.finalshopping.model.User;

import java.util.List;

public interface CartService {

    Cart newCart(User user);
    void deleteCart(int id);
    Cart getCartById(int id);
    Cart getCartByUser(User user);
    List<Cart> getAll();
    Sale buy(Cart cart, Sale.Mode mode);
}
