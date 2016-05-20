package com.globant.bootcamp.finalshopping.controllers;


import com.globant.bootcamp.finalshopping.model.Cart;
import com.globant.bootcamp.finalshopping.model.Sale;
import com.globant.bootcamp.finalshopping.repositories.CartRepository;
import com.globant.bootcamp.finalshopping.repositories.ProductRepository;
import com.globant.bootcamp.finalshopping.repositories.SessionRepository;
import com.globant.bootcamp.finalshopping.repositories.UserRepository;
import com.globant.bootcamp.finalshopping.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private SessionService sessions;
    private CartService carts;
    private CatalogService catalog;
    private UserService users;

    @Autowired
    CartController(SessionRepository repoSession,
                   CartRepository repoCart,
                   ProductRepository repoProd,
                   UserRepository repoUser){
        sessions = SessionServiceFactory.getSessionServiceJPA(repoSession);
        carts = CartServiceFactory.getCartServiceJPA(repoCart);
        catalog = CatalogServiceFactory.getCatalogServiceJPA(repoProd);
        users = UserServiceFactory.getUserServiceJPA(repoUser);
    }

    @RequestMapping(value = "/{userName}/cart", method = RequestMethod.GET)
    public ResponseEntity<Cart> getCart(@PathVariable String userName, @RequestParam String token){

        if(sessions.check(token, userName))
            return new ResponseEntity<Cart>(carts.getCartByUser(users.findByName(userName)), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/{userName}/cart", method = RequestMethod.POST)
    public ResponseEntity<Cart> postCart(@PathVariable String userName,
                                         @RequestParam String token,
                                         @RequestParam int idProd){

        if(sessions.check(token, userName)){
            try {
                Cart cart = carts.getCartByUser(users.findByName(userName));
                if(cart == null)
                    cart = carts.newCart(users.findByName(userName));

                if (cart.addItem(catalog.getProductById(idProd)))
                    return new ResponseEntity<>(cart, HttpStatus.OK);
                else
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } catch (NullPointerException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/{userName}/cart", method = RequestMethod.DELETE)
    public ResponseEntity<Cart> delCart(@PathVariable String userName,
                                         @RequestParam String token,
                                         @RequestParam int idProd){

        if(sessions.check(token, userName)){
            Cart cart = carts.getCartByUser(users.findByName(userName));
            if(cart.removeItem(catalog.getProductById(idProd)))
                return new ResponseEntity<>(cart, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/{userName}/cart/buy", method = RequestMethod.GET)
    public ResponseEntity<Sale> buyCart(@PathVariable String userName,
                                        @RequestParam String token,
                                        @RequestParam Sale.Mode mode){

        if(sessions.check(token, userName)){
            Cart cart = carts.getCartByUser(users.findByName(userName));
            if(cart != null)
                return new ResponseEntity<>(carts.buy(cart, mode), HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
