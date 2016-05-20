package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.repositories.CartRepository;

/**
 * Created by root on 5/19/16.
 */
public class CartServiceFactory {

    public static CartService getCartServiceJPA(CartRepository repository){
        return new CartServiceJPA(repository);
    }
}
