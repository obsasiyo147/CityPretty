package com.example.cityprettyapp;


import java.io.Serializable;

public class Services implements Serializable {
    private String name;
    private String price;
    private String description;
    private String id;

    public Services(){

    };

    public Services(String newName, String newPrice, String newDescription, String newId){
        this.name = newName;
        this.price = newPrice;
        this.description = newDescription;
        this.id = newId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "Service{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\''+
                ", description'" + description + '\'' +
                '}';
    }
}
