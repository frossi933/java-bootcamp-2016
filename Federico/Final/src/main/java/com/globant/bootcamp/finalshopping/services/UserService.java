package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.model.User;

public interface UserService {

    boolean add(User u);
    boolean remove(int id);
    boolean update(User u);
    Iterable<User> findAll();
    User findById(int id);
    User findByName(String name);

}
