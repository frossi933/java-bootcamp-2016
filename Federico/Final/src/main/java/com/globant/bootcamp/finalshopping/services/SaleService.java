package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.model.Cart;
import com.globant.bootcamp.finalshopping.model.Sale;
import com.globant.bootcamp.finalshopping.model.User;

import java.util.List;


public interface SaleService {

    Sale createSale(Cart cart, Sale.Mode mode);
    boolean deleteSale(int id);
    List<Sale> getAll();
    List<Sale> getByUser(User user);
}
