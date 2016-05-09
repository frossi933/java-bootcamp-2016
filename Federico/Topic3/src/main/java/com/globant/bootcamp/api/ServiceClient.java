package com.globant.bootcamp.api;

import java.util.LinkedList;
import java.util.Random;

public class ServiceClient {

    private final static int N_PROD = 30;
    private final static int SEED = 2016;

    private static Product createRandomProd(Random random){

        Integer rand_num = random.nextInt();
        int rand_price = random.nextInt();
        String rand_name = rand_num.toString();

        return new Product(rand_name, rand_price);
    }

    public static void main(String[] args){

        /* Create and fill a new catalog */
        ServiceCatalog catalog = ServiceCatalogFactory.getServiceCatalogHashMap();

        Random random = new Random(SEED);
        for(int i = 0;i<N_PROD;i++){
            Product prod = createRandomProd(random);
            catalog.newProduct(prod.getId(), prod);
        }

        /* Simulate a client purchase */
        ServiceShoppingCart cart = ServiceShoppingCartFactory.getShoppingCartImp();
        cart.setPaymentMethod("Credit Card");

        LinkedList<Product> prods = catalog.getAllProducts();
        cart.addProduct(prods.get(0));
        cart.addMultipleProducts(prods.get(3), 4);
        cart.addProduct(prods.get(10));
        cart.rmProduct(prods.get(3));

        Sale sale = cart.buy();
        System.out.print(sale.toString());
    }
}
