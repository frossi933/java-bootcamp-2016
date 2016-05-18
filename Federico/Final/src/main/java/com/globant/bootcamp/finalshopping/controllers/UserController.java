package com.globant.bootcamp.finalshopping.controllers;

import com.globant.bootcamp.finalshopping.model.Session;
import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.SessionRepository;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import com.globant.bootcamp.finalshopping.services.SessionService;
import com.globant.bootcamp.finalshopping.services.SessionServiceFactory;
import com.globant.bootcamp.finalshopping.services.UserService;
import com.globant.bootcamp.finalshopping.services.UserServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService users;
    private SessionService sessions;

    @Autowired
    UserController(UserRepository repoUser, SessionRepository repoSession){
        users = UserServiceFactory.getUserServiceJPA(repoUser);
        sessions = SessionServiceFactory.getSessionServiceJPA(repoSession);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    ResponseEntity<User> signup(@RequestBody User user){
        if(users.add(user))
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    ResponseEntity<Session> signin(@RequestParam String name, @RequestParam String pass){

        User user = users.findByName(name);
        if(user == null || !user.getPassword().equals(pass))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        Session session = new Session(name);

        if(sessions.add(session))
            return new ResponseEntity<>(session, HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    ResponseEntity<User> getUser(@PathVariable String userName, @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<User>(users.findByName(userName),HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/{userName}/config", method = RequestMethod.PUT)
    ResponseEntity<User> updateFields(@PathVariable String userName, @RequestParam String token, @RequestBody User newUser){
        if(sessions.check(token, userName))
            if(users.update(newUser))
                return new ResponseEntity<User>(newUser,HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



}
