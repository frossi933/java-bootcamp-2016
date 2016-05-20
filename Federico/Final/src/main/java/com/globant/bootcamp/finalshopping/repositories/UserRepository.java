package com.globant.bootcamp.finalshopping.repositories;

import com.globant.bootcamp.finalshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByName(String name);
}