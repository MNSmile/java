package com.pw.builder;

public interface Builder {
    public void buildBasic();  //构建地基
    public void buildWalls();  //构建墙
    public void buildRoofed(); //构建房顶
    House buildHouse();   //建房子
}
