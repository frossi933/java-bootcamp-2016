package com.globant.bootcamp.finalshopping.controllers;


import com.globant.bootcamp.finalshopping.model.Cart;
import com.globant.bootcamp.finalshopping.model.Product;
import com.globant.bootcamp.finalshopping.model.Sale;
import com.globant.bootcamp.finalshopping.repositories.*;
import com.globant.bootcamp.finalshopping.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Api(value = "Shopping Cart", description = "It performs all shopping cart operation", produces = "application/json")
public class CartController {

    private SessionService sessions;
    private CartService carts;
    private CatalogService catalog;
    private UserService users;
    private SaleService sales;

    @Autowired
    CartController(SessionRepository repoSession,
                   CartRepository repoCart,
                   ProductRepository repoProd,
                   UserRepository repoUser,
                   SaleRepository repoSale){
        sessions = SessionServiceFactory.getSessionServiceJPA(repoSession);
        carts = CartServiceFactory.getCartServiceJPA(repoCart);
        catalog = CatalogServiceFactory.getCatalogServiceJPA(repoProd);
        users = UserServiceFactory.getUserServiceJPA(repoUser);
        sales = SaleServiceFactory.getSaleServiceJPA(repoSale);
    }

    @RequestMapping(value = "/user/{userName}/cart", method = RequestMethod.GET)
    @ApiOperation(value = "Get cart object", response = ResponseEntity.class, notes = "Returns the user current cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<Cart> getCart(@PathVariable String userName, @RequestParam String token){

        if(sessions.check(token, userName))
            return new ResponseEntity<>(carts.getCartByUser(users.findByName(userName)), HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/cart", method = RequestMethod.POST)
    @ApiOperation(value = "Add a new product", response = ResponseEntity.class, notes = "Returns the updated cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<Cart> postCart(@PathVariable String userName,
                                         @RequestParam String token,
                                         @RequestParam int idProd){

        if(sessions.check(token, userName)){
            Product prod = catalog.getProductById(idProd);
            if(prod == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

            Cart cart = carts.getCartByUser(users.findByName(userName));
            if(cart == null)
                cart = carts.newCart(users.findByName(userName));

            if (cart.addItem(prod))
                return new ResponseEntity<>(cart, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/cart", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove a product", response = ResponseEntity.class, notes = "Returns the updated cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 200, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<Cart> delCart(@PathVariable String userName,
                                         @RequestParam String token,
                                         @RequestParam int idProd){

        if(sessions.check(token, userName)){
            Cart cart = carts.getCartByUser(users.findByName(userName));
            Product prod = catalog.getProductById(idProd);
            if(cart != null && prod != null && cart.removeItem(prod))
                return new ResponseEntity<>(cart, HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/cart/buy", method = RequestMethod.GET)
    @ApiOperation(value = "Close purchase", response = ResponseEntity.class, notes = "Returns the final sale")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 200, message = "Operation failed. Bad arguments"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<Sale> buyCart(@PathVariable String userName,
                                        @RequestParam String token,
                                        @RequestParam Sale.Mode mode){

        if(sessions.check(token, userName)){
            Cart cart = carts.getCartByUser(users.findByName(userName));
            if(cart != null)
                return new ResponseEntity<>(sales.createSale(cart, mode), HttpStatus.OK);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/user/{userName}/cart/history", method = RequestMethod.GET)
    @ApiOperation(value = "Show User history", response = ResponseEntity.class, notes = "Returns a list with all user purchases")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Operation failed. User is not logged in")
    })
    public ResponseEntity<List<Sale>> showHistory(@PathVariable String userName,
                                                  @RequestParam String token) {

        if (sessions.check(token, userName)) {
            return new ResponseEntity<>(sales.getByUser(users.findByName(userName)), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/admin/cart/buys", method = RequestMethod.GET)
    @ApiOperation(value = "Show All history", response = ResponseEntity.class, notes = "Returns a list with all sales")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 401, message = "Operation failed")
    })
    public ResponseEntity<List<Sale>> showBuysHistory(@RequestParam String token) {

        if (sessions.check(token, "admin")) {
            return new ResponseEntity<>(sales.getAll(), HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}