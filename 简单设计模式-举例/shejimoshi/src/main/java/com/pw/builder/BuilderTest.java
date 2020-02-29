package com.pw.builder;

public class BuilderTest {
    public static void main(String[] args) {
        Director director = new Director(new ConcreteBuilder());
        House house = director.costructHouse();
        System.out.println(house);
    }
}
