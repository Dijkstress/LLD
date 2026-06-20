package com.example.entities;

import com.example.enums.DiscColor;

public class Player {
    private String name;
    private DiscColor color;

    public Player(String name, DiscColor color){
        this.name=name;
        this.color=color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiscColor getColor() {
        return color;
    }

    public void setColor(DiscColor color) {
        this.color = color;
    }
}
