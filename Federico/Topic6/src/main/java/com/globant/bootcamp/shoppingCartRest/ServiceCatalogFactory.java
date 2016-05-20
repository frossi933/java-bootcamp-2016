package com.globant.bootcamp.shoppingCartRest;

public class ServiceCatalogFactory {

    private ServiceCatalogFactory () {}

    public static ServiceCatalog getServiceCatalogHashMap(){ return new ServiceCatalogHashMap(); }
    public static ServiceCatalog getServiceCatalogJPA(ProductRepository repo){
        return new ServiceCatalogJPA(repo); }
}
