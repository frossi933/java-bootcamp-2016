package com.globant.bootcamp.finalshopping;

import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new User("Jack"));
            repository.save(new User("Chloe"));

            // fetch all customers
            /*g.info("Customers found with findAll():");
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
            log.info("");*/
        };
    }

}
