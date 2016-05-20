package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.model.Product;
import com.globant.bootcamp.finalshopping.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CatalogServiceJPA implements  CatalogService {

    private ProductRepository repo;

    @Autowired
    public CatalogServiceJPA(ProductRepository repo){
        this.repo=repo;
    }

    @Override
    public boolean addProduct(Product product) {
        if(repo.exists(product.getId()))
            return false;

        repo.save(product);
        return true;
    }

    @Override
    public boolean removeProduct(int id) {
        if(!repo.exists(id))
            return false;

        repo.delete(id);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        if(!repo.exists(product.getId()))
            return false;

        repo.save(product);
        return true;
    }

    @Override
    public Product getProductById(int id) {
        return repo.findOne(id);
    }

    @Override
    public Product getProductByName(String name) {
        return repo.getByName(name);
    }

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        return repo.findByCategory(category);
    }

    @Override
    public List<Product> getAll(){
        return repo.findAll();
    }
}
