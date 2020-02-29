package com.pw.builder;

public class HutBuilder implements Builder {
    private House house;

    public HutBuilder() {
        this.house = new House();
    }

    @Override
    public void buildBasic() {
        house.setBasic("泥土");
    }

    @Override
    public void buildWalls() {
        house.setWall("泥土");
    }

    @Override
    public void buildRoofed() {
        house.setRoofed("稻草");
    }

    @Override
    public House buildHouse() {
        return house;
    }
}
