package com.globant.bootcamp.finalshopping.controllers;


import com.globant.bootcamp.finalshopping.model.Product;
import com.globant.bootcamp.finalshopping.repositories.ProductRepository;
import com.globant.bootcamp.finalshopping.repositories.SessionRepository;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import com.globant.bootcamp.finalshopping.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CatalogController {

    private SessionService sessions;
    private CatalogService catalog;

    @Autowired
    CatalogController(SessionRepository repoSession, ProductRepository repoProduct){
        sessions = SessionServiceFactory.getSessionServiceJPA(repoSession);
        catalog = CatalogServiceFactory.getCatalogServiceJPA(repoProduct);
    }


    @RequestMapping(value = "/admin/catalog", method = RequestMethod.POST)
    public ResponseEntity<Product> postProduct(@RequestParam String token, @RequestBody Product product){
        if(sessions.check(token,"admin")) {
            if (catalog.addProduct(product))
                return new ResponseEntity<Product>(product, HttpStatus.CREATED);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/admin/catalog", method = RequestMethod.DELETE)
    public ResponseEntity<Product> delProduct(@RequestParam String token, @RequestParam int id){

        if(sessions.check(token, "admin")){
            Product toRemove = catalog.getProductById(id);
            if(catalog.removeProduct(id))
                return new ResponseEntity<Product>(toRemove, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/admin/catalog", method = RequestMethod.PUT)
    public ResponseEntity<Product> putProduct(@RequestParam String token, @RequestBody Product product){

        if(sessions.check(token, "admin")){
            if(catalog.updateProduct(product) || catalog.addProduct(product))
                return new ResponseEntity<>(product, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/{userName}/catalog", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductsUser(@PathVariable String userName, @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<List<Product>>(catalog.getAll(), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/{userName}/catalog/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable String userName,
                                                             @PathVariable Product.Category category,
                                                             @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<>(catalog.getProductsByCategory(category), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/{userName}/catalog/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductUser(@PathVariable String userName, @RequestParam int id, @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<Product>(catalog.getProductById(id), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
