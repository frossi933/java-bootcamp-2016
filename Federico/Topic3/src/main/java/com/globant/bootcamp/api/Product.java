package com.globant.bootcamp.api;

public class Product {

    private String id;
    private String name;
    private double price;
    private String desc;

    public Product(String name, double price){
        this.price = price;
        this.name = name;
        Integer code = name.hashCode();
        this.id = code.toString();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
        Integer code = name.hashCode();
        this.id = code.toString();
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                '}';
    }
}
