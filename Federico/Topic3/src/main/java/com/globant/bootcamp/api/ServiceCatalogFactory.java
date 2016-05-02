package com.globant.bootcamp.api;

public class ServiceCatalogFactory {

    private ServiceCatalogFactory () {}

    public static ServiceCatalog getServiceCatalogHashMap(){ return new ServiceCatalogHashMap(); }
}
