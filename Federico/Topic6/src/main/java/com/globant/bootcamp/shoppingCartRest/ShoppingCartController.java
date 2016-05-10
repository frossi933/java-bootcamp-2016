package com.globant.bootcamp.shoppingCartRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class ShoppingCartController {

    private ServiceShoppingCart cart;

    protected ShoppingCartController(){
        cart = ServiceShoppingCartFactory.getShoppingCartImp();
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(cart.getProductList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public ResponseEntity<Product> postProduct(@RequestBody Product prod){
        if(cart.addProduct(prod))
            return new ResponseEntity<>(prod, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@RequestBody Product prod){
        if(cart.rmProduct(prod))
            return new ResponseEntity<>("product deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("product not deleted", HttpStatus.BAD_REQUEST);
    }
}
