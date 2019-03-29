package com.example.cityprettyapp;


import java.io.Serializable;

public class Services implements Serializable {
    private long name;
    private long price;
    private long description;
    private long id;

    public Services(){

    };

    public Services(long newName, long newPrice, long newDescription, long newId){
        this.name = newName;
        this.price = newPrice;
        this.description = newDescription;
        this.id = newId;
    }

    public long getName() {
        return name;
    }

    public long getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }

    public void setName (long name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setDescription(long description) {
        this.description = description;
    }
}
