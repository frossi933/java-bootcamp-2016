package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.model.Session;

public interface SessionService {

    String create(String username);
    boolean check(String token, String username);


}
