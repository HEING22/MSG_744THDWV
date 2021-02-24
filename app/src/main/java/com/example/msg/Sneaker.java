package com.example.msg;

import java.io.Serializable;

public class Sneaker implements Serializable {
    public String id;
    public String name;
    public String brand;
    public String image;
    public String location;
    public String detail;
    public String R_price;
    public String R_year;


    public Sneaker() {
    }

    public Sneaker(String id, String name, String brand, String image, String location, String detail, String r_price, String r_year) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.image = image;
        this.location = location;
        this.detail = detail;
        this.R_price = r_price;
        this.R_year = r_year;
    }
}
