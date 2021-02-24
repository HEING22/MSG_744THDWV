package com.example.msg;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String phone;
    public String gender;
    public String password;

    public User() {
    }

    public User(String name, String phone, String gender, String password) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
    }
}
