package com.globant.bootcamp.shoppingCartRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    private ServiceCatalog catalog = null;

    protected CatalogController (){
        catalog = ServiceCatalogFactory.getServiceCatalogHashMap();
    }

    // PREGUNTAR COMO HACER
    @RequestMapping(value = "/catalogs",method = RequestMethod.GET)
    public List<Product> getProducts(){
        return catalog.getAllProducts();
    }

    @RequestMapping(value = "/catalog",method = RequestMethod.GET)
    public Product getProduct(@RequestParam(value="id") String id){
        return catalog.getProduct(id);
    }

    @RequestMapping(value = "/catalog",method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Product> postProduct(@RequestBody Product prod){
        if(catalog.newProduct(prod.getId(), prod))
            return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/catalog",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@RequestParam(value="id") String id){
        if(catalog.deleteProduct(id))
            return new ResponseEntity<String>("Product deleted", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product not deleted", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/catalog",method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestBody Product prod){
        if(catalog.updateProduct(prod.getId(), prod))
            return new ResponseEntity<Product>(prod, HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
