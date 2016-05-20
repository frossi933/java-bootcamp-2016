package com.globant.bootcamp.finalshopping.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ApiModel(value = "User entity", description = "Represents info of a user")
public class User {

    @Id @GeneratedValue
    @ApiModelProperty(value = "User identifier")
    private int id;

    @ApiModelProperty(value = "Name of the user, used to login", required = true)
    private String name;
    @ApiModelProperty(value = "Password of the user, used to login", required = true)
    private String password;

    public User (){}

    public User(String name){
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        return getName() != null ? getName().equals(user.getName()) : user.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
