package com.globant.bootcamp.finalshopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.globant.bootcamp.finalshopping.model.Product.Category.OTHER;

@Entity
@Table(name = "Catalog")
public class Product {

    public enum Category {
        FURNITURE, ELECTRO, FOOD, BOOKS, OTHER
    }

    @Id @GeneratedValue
    private int id;

    private String name;
    private String description;
    private Category category;

    /* represents pennies */
    private long price;

    public Product(){}

    public Product(String name, long price){
        this.name=name;
        this.price=price;
        this.category=OTHER;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {   return category; }

    public String getDesc() {
        return description;
    }

    public long getPrice() { return price;  }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public void setCategory(Category category) {    this.category = category;  }

    public void setPrice(long price) { this.price = price;  }

    // implementar hash to string...
}
