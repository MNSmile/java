package com.pw.builder;

public class ConcreteBuilder implements Builder {
    private House house;

    public ConcreteBuilder() {
        this.house = new House();
    }

    @Override
    public void buildBasic() {
        house.setBasic("混泥土");
    }

    @Override
    public void buildWalls() {
        house.setWall("混泥土");
    }

    @Override
    public void buildRoofed() {
        house.setRoofed("钢化结构");
    }

    @Override
    public House buildHouse() {
        return house;
    }
}
