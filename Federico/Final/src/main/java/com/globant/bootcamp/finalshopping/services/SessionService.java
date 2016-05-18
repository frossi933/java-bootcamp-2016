package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.model.Session;

public interface SessionService {

    boolean add(Session session);
    boolean check(String token, String username);


}
