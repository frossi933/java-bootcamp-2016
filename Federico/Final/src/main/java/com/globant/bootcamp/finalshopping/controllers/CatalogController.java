package com.globant.bootcamp.finalshopping.controllers;


import com.globant.bootcamp.finalshopping.model.Product;
import com.globant.bootcamp.finalshopping.repositories.ProductRepository;
import com.globant.bootcamp.finalshopping.repositories.SessionRepository;
import com.globant.bootcamp.finalshopping.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "Catalog", description = "It contains all available products",produces = "application/json")
public class CatalogController {

    private SessionService sessions;
    private CatalogService catalog;

    @Autowired
    CatalogController(SessionRepository repoSession, ProductRepository repoProduct){
        sessions = SessionServiceFactory.getSessionServiceJPA(repoSession);
        catalog = CatalogServiceFactory.getCatalogServiceJPA(repoProduct);
    }


    @RequestMapping(value = "/admin/catalog", method = RequestMethod.POST)
    @ApiOperation(value = "Add a new product", response = ResponseEntity.class, notes = "Returns the added product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "New product added"),
            @ApiResponse(code = 400, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation failed")
    })
    public ResponseEntity<Product> postProduct(@RequestParam String token, @RequestBody Product product){
        if(sessions.check(token,"admin")) {
            if (catalog.addProduct(product))
                return new ResponseEntity<>(product, HttpStatus.CREATED);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/admin/catalog", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove a product", response = ResponseEntity.class, notes = "Returns the removed product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product removed"),
            @ApiResponse(code = 400, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation failed")
    })
    public ResponseEntity<Product> delProduct(@RequestParam String token, @RequestParam int id){

        if(sessions.check(token, "admin")){
            Product toRemove = catalog.getProductById(id);
            if(catalog.removeProduct(id))
                return new ResponseEntity<>(toRemove, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/admin/catalog", method = RequestMethod.PUT)
    @ApiOperation(value = "Modify or add a product", response = ResponseEntity.class, notes = "Returns the added or updated product")
    @ApiResponses({
            @ApiResponse(code = 202, message = "New product updated"),
            @ApiResponse(code = 400, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation failed")
    })
    public ResponseEntity<Product> putProduct(@RequestParam String token, @RequestBody Product product){

        if(sessions.check(token, "admin")){
            if(catalog.updateProduct(product) || catalog.addProduct(product))
                return new ResponseEntity<>(product, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/catalog", method = RequestMethod.GET)
    @ApiOperation(value = "Get product list", response = ResponseEntity.class, notes = "Returns the full list of available products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<List<Product>> getProductsUser(@PathVariable String userName, @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<>(catalog.getAll(), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/catalog/category/{category}", method = RequestMethod.GET)
    @ApiOperation(value = "Get product list by category", response = ResponseEntity.class,
            notes = "Returns the full list of available products of the specific category")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable String userName,
                                                             @PathVariable Product.Category category,
                                                             @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<>(catalog.getProductsByCategory(category), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/catalog/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get product info", response = ResponseEntity.class, notes = "Returns all information about the product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<Product> getProductUser(@PathVariable String userName, @RequestParam int id, @RequestParam String token){
        if(sessions.check(token, userName))
            return new ResponseEntity<>(catalog.getProductById(id), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
