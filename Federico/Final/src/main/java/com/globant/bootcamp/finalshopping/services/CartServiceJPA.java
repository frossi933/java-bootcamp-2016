package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.model.Cart;
import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartServiceJPA implements CartService {

    private CartRepository repo;

    @Autowired
    public CartServiceJPA(CartRepository repoCart){

        this.repo=repoCart;
    }

    @Override
    public Cart newCart(User user) {
        Cart old = repo.findByUser(user);
        if(old != null)
            return old;

        Cart cart = new Cart(user);
        repo.save(cart);
        return cart;
    }

    @Override
    public void deleteCart(int id) {
        repo.delete(id);
    }

    @Override
    public Cart getCartById(int id) {
        return repo.findOne(id);
    }

    @Override
    public Cart getCartByUser(User user) {
        return repo.findByUser(user);
    }

    @Override
    public List<Cart> getAll() {
        return repo.findAll();
    }
}
