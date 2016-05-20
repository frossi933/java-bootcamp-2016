package com.globant.bootcamp.shoppingCartRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {

    private ServiceShoppingCart cart;

    @Autowired
    protected ShoppingCartController(ProductRepository repository){

        //cart = ServiceShoppingCartFactory.getShoppingCartImp();
        cart = ServiceShoppingCartFactory.getShoppingCartJPA(repository);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getProducts(){
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
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "id") long id){
        if(cart.rmProduct(id))
            return new ResponseEntity<>("product deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("product not deleted", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.PUT)
    public ResponseEntity<String> putProduct(@RequestParam(value = "id") long id, @RequestBody Product prod){
        if(prod.getId() != id)
            return new ResponseEntity<>("Incorrect id", HttpStatus.BAD_REQUEST);

        if(cart.addProduct(prod))
            return new ResponseEntity<>("Product updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Product not updated", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value= "/cart/buy", method = RequestMethod.GET)
    public ResponseEntity<Sale> getSale(@RequestParam(value = "payment", defaultValue = "Cash") String payment){
        if(cart.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return new ResponseEntity<Sale>(new Sale(cart.getProductList(),payment), HttpStatus.OK);
    }
}
