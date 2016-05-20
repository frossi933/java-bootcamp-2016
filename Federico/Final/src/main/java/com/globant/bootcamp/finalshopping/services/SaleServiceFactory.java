package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.repositories.SaleRepository;

public class SaleServiceFactory {

    public static SaleService getSaleServiceJPA(SaleRepository repo){
        return new SaleServiceJPA(repo);
    }
}
