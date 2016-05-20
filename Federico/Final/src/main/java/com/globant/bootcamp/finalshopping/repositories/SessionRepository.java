package com.globant.bootcamp.finalshopping.repositories;


import com.globant.bootcamp.finalshopping.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {

    Session findByUsername(String username);
}
