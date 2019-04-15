package com.example.cityprettyapp;

public class Store {
    private String address;
    private String name;
    private String serviceNumber;

Store(String newAddress, String newName, String newServiceNumber){
  address = newAddress;
  name = newName;
  serviceNumber = newServiceNumber;

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

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }
}
