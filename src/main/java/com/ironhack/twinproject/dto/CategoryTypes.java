package com.ironhack.twinproject.dto;


public enum CategoryTypes {
    HISTORY(4),
    MUSIC(88),
    OLYMPICS(9),
    CARS(110),
    CITIES(1);
    //Instance variable
    private int value;
    //Constructor to initialize the instance variable
    CategoryTypes(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }

}