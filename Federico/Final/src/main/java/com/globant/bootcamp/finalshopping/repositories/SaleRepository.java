package com.globant.bootcamp.finalshopping.repositories;

import com.globant.bootcamp.finalshopping.model.Sale;
import com.globant.bootcamp.finalshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByUser(User user);
}
