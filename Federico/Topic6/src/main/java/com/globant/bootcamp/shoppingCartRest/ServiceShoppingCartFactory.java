package com.globant.bootcamp.shoppingCartRest;

public class ServiceShoppingCartFactory {

    private ServiceShoppingCartFactory() {}

    public static ServiceShoppingCart getShoppingCartImp(){
        return new ServiceShoppingCartImp();
    }

    /* Another possible implementation, which uses a database:

    public ServiceShoppingCart getShoppingCartSqlImp(){

    }
     */
}
