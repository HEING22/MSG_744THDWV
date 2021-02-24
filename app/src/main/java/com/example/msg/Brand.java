package com.example.msg;

import java.io.Serializable;

public class Brand implements Serializable {
    public String id;
    public String name;

    public Brand() {
    }

    public Brand(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
