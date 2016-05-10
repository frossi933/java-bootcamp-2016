package com.globant.bootcamp.shoppingCartRest;

public class ServiceCatalogFactory {

    private ServiceCatalogFactory () {}

    public static ServiceCatalog getServiceCatalogHashMap(){ return new ServiceCatalogHashMap(); }
}
