package org.launchcode.foodie.models;

public enum RestaurantType {

    American ("American"),
    Chinese ("Chinese"),
    Mexican ("Mexican");


    private final String name;

    RestaurantType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


