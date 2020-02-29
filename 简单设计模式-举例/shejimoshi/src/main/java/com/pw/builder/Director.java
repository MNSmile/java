package com.pw.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public House costructHouse(){
        builder.buildBasic();
        builder.buildWalls();
        builder.buildRoofed();
        return builder.buildHouse();
    }
}
