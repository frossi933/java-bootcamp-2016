package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.repositories.UserRepository;

/**
 * Created by root on 5/16/16.
 */
public class UserServiceFactory {

    public static UserService getUserServiceJPA(UserRepository repo) {

        return new UserServiceJPA(repo);
    }
}
