package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.model.Cart;
import com.globant.bootcamp.finalshopping.model.Sale;
import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SaleServiceJPA implements SaleService {

    private SaleRepository repo;

    @Autowired
    public SaleServiceJPA(SaleRepository repo){
        this.repo=repo;
    }

    @Override
    public Sale createSale(Cart cart, Sale.Mode mode) {
        Sale ret = new Sale(cart, mode);
        repo.save(ret);
        return ret;
    }

    @Override
    public boolean deleteSale(int id) {
        if(repo.exists(id)) {
            repo.delete(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Sale> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Sale> getByUser(User user) {
        return repo.findByUser(user);
    }
}
