package com.globant.bootcamp.shoppingCartRest;


import org.springframework.beans.factory.annotation.Autowired;

public class ServiceCatalogJPA implements ServiceCatalog {

    private ProductRepository repo;

    @Autowired
    public ServiceCatalogJPA(ProductRepository repo){
        this.repo = repo;
    }

    @Override
    public boolean newProduct(long id, Product prod) {
        if(repo.exists(id))
            return false;

        repo.save(prod);
        return true;
    }

    @Override
    public boolean deleteProduct(long id) {
        if(!repo.exists(id))
            return false;

        repo.delete(id);
        return true;
    }

    @Override
    public boolean updateProduct(long id, Product newProd) {
        if(repo.exists(id)) {
            repo.save(newProd);
            return true;
        }

        return false;
    }

    @Override
    public Product getProduct(long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return repo.findAll();
    }
}
