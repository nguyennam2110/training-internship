package com.training.app.entity.model;

import com.training.app.entity.enums.Gender;

public class Room {
    private Integer id;
    private String name;
    private int number;
    public Room() {}

    public Room(Integer id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}




