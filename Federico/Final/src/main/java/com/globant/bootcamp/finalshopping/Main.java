package com.globant.bootcamp.finalshopping;

import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
            User admin = repository.getByName("admin");
            if(admin == null){
                admin = new User("admin");
                admin.setPassword("admin");
                repository.save(admin);
            }

        };
    }

}
