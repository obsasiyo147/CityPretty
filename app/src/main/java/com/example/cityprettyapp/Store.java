package com.example.cityprettyapp;

public class Store {
    private String address;
    private String name;

Store(String newAddress, String newName){
  address = newAddress;
  name = newName;
}
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
