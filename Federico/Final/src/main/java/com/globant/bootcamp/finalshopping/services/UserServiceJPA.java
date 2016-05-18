package com.globant.bootcamp.finalshopping.services;

import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by root on 5/16/16.
 */
public class UserServiceJPA implements UserService {

    private UserRepository repo;

    @Autowired
    UserServiceJPA(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public boolean add(User u) {
        if(repo.exists(u.getId()))
            return false;

        repo.save(u);
        return true;
    }

    @Override
    public boolean remove(int id) {
        if(!repo.exists(id))
            return false;

        repo.delete(id);
        return true;
    }

    @Override
    public boolean update(User u) {
        if(!repo.exists(u.getId()))
            return false;

        repo.save(u);
        return true;
    }

    @Override
    public Iterable<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(int id) {
        return repo.findOne(id);
    }

    @Override
    public User findByName(String name){
        return repo.findByName(name);
    }
}
