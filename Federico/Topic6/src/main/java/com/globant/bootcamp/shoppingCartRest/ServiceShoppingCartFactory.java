package com.globant.bootcamp.shoppingCartRest;

public class ServiceShoppingCartFactory {

    private ServiceShoppingCartFactory() {}

    public static ServiceShoppingCart getShoppingCartImp(){
        return new ServiceShoppingCartImp();
    }

    public static ServiceShoppingCart getShoppingCartJPA(ProductRepository repository){
        return new ServiceShoppingCartJPA(repository);
    }
}
