package com.globant.bootcamp.shoppingCartRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "id") String id){
        if(cart.rmProduct(id))
            return new ResponseEntity<>("product deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("product not deleted", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.PUT)
    public ResponseEntity<String> putProduct(@RequestParam(value = "id") String id, @RequestBody Product prod){
        if(prod.getId() != id)
            return new ResponseEntity<>("Incorrect id", HttpStatus.BAD_REQUEST);

        if(cart.rmProduct(id) && cart.addProduct(prod))
            return new ResponseEntity<>("Product updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Product not updated", HttpStatus.BAD_REQUEST);
    }
}
