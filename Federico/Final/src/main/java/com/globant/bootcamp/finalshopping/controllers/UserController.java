package com.globant.bootcamp.finalshopping.controllers;

import com.globant.bootcamp.finalshopping.model.User;
import com.globant.bootcamp.finalshopping.repositories.SessionRepository;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import com.globant.bootcamp.finalshopping.services.SessionService;
import com.globant.bootcamp.finalshopping.services.SessionServiceFactory;
import com.globant.bootcamp.finalshopping.services.UserService;
import com.globant.bootcamp.finalshopping.services.UserServiceFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "Users", description = "Users API, to create a new user, login and change user configurations",produces = "application/json")
public class UserController {

    private UserService users;
    private SessionService sessions;

    @Autowired
    UserController(UserRepository repoUser, SessionRepository repoSession){
        users = UserServiceFactory.getUserServiceJPA(repoUser);
        sessions = SessionServiceFactory.getSessionServiceJPA(repoSession);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiOperation(value = "Sign Up", response = ResponseEntity.class, notes = "Returns the user object created")
    @ApiResponses({
            @ApiResponse(code = 201, message = "New user created"),
            @ApiResponse(code = 400, message = "Operation Failed. User already exists or bad arguments")
    })
    ResponseEntity<User> signup(@RequestBody User user){
        if(users.add(user))
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ApiOperation(value = "Sign In", response = ResponseEntity.class, notes = "Returns a session token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User logged. New token generated"),
            @ApiResponse(code = 400, message = "Operation Failed. User doesn't exist or bad arguments")
    })
    ResponseEntity<String> signin(@RequestParam String name, @RequestParam String pass){

        User user = users.findByName(name);
        if(user == null || !user.getPassword().equals(pass))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        String token = sessions.create(name);
        if(token != null)
            return new ResponseEntity<>(token, HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    @ApiOperation(value = "Get User info", response = ResponseEntity.class, notes = "Returns user information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exists User"),
            @ApiResponse(code = 401, message = "Operation Failed. Incorrect token. User is not logged in")
    })
    ResponseEntity<User> getUser(@PathVariable String userName, @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<>(users.findByName(userName),HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/config", method = RequestMethod.PUT)
    @ApiOperation(value = "Change User conf", response = ResponseEntity.class, notes = "Returns the new user configuration")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Conf updated"),
            @ApiResponse(code = 400, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation Failed. Incorrect token. User is not logged in")
    })
    ResponseEntity<User> updateFields(@PathVariable String userName, @RequestParam String token, @RequestBody User newUser){
        if(sessions.check(token, userName))
            if(users.update(newUser))
                return new ResponseEntity<>(newUser,HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
