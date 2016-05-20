package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.repositories.ProductRepository;

public class CatalogServiceFactory {

    public static CatalogService getCatalogServiceJPA(ProductRepository repository){
        return new CatalogServiceJPA(repository);
    }
}
