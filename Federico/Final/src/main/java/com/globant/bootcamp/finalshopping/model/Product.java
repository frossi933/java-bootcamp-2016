package com.globant.bootcamp.finalshopping.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.globant.bootcamp.finalshopping.model.Product.Category.OTHER;

@Entity
@Table(name = "Catalog")
@ApiModel(value = "Product entity", description = "Complete info of a product")
public class Product {

    public enum Category {
        FURNITURE, ELECTRO, FOOD, BOOKS, OTHER
    }

    @Id @GeneratedValue
    @ApiModelProperty(value = "Product identifier")
    private int id;

    @ApiModelProperty(value = "Product name", required = true)
    private String name;
    @ApiModelProperty(value = "Product extended description")
    private String description;
    @ApiModelProperty(value = "Product category. It could be FURNITURE,ELECTRO,FOOD,BOOKS,OTHER(default)")
    private Category category;
    @ApiModelProperty(value = "Product price represented by pennies")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return getId() == product.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
