package com.example.cityprettyapp;

public class StoreBeauticianSide {
     private String address;
     private String name;
     private String date;
     private String time;
     private String email;

        StoreBeauticianSide(String newAddress, String newName, String newTime, String newDate, String newEmail){
            address = newAddress;
            name = newName;
            time = newTime;
            email = newEmail;
            date = newDate;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

