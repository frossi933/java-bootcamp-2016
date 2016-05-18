package com.globant.bootcamp.finalshopping.services;


import com.globant.bootcamp.finalshopping.repositories.SessionRepository;

public class SessionServiceFactory {

    public static SessionService getSessionServiceJPA(SessionRepository repo){
        return new SessionServiceJPA(repo);
    }
}
