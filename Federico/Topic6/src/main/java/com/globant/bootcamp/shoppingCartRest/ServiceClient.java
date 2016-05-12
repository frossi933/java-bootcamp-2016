package com.globant.bootcamp.shoppingCartRest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Logger;

@SpringBootApplication
public class ServiceClient {

/*    private final static int N_PROD = 30;
    private final static int SEED = 2016;

    private static Product createRandomProd(Random random){

        Integer rand_num = random.nextInt();
        int rand_price = random.nextInt();
        String rand_name = rand_num.toString();

        return new Product(rand_name, rand_price);
    }

    public static void main(String[] args){

        // Create and fill a new catalog
        ServiceCatalog catalog = ServiceCatalogFactory.getServiceCatalogHashMap();

        Random random = new Random(SEED);
        for(int i = 0;i<N_PROD;i++){
            Product prod = createRandomProd(random);
            catalog.newProduct(prod.getId(), prod);
        }

        // Simulate a client purchase
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
*/
    private static final Logger log = Logger.getLogger(ServiceClient.class.getName());

    public static void main(String[] args){
        SpringApplication.run(ServiceClient.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Product("Jack", 2.0));
            repository.save(new Product("Chloe", 3.0));
            repository.save(new Product("Kim", 4.0));
            repository.save(new Product("David", 5.6));
            repository.save(new Product("Michelle", 1.5));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Product p : repository.findAll()) {
                log.info(p.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Product prod = repository.findOne(1L);
            log.info("Product found with findOne(1L):");
            log.info("--------------------------------");
            log.info(prod.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByName('Jack'):");
            log.info("--------------------------------------------");
            for (Product bauer : repository.findByName("Jack")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}
