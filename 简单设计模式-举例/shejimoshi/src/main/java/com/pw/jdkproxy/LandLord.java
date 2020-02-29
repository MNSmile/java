package com.pw.jdkproxy;


/**
 * 具体的被代理对象
 */
public class LandLord implements HouseManager{

    @Override
    public void sellHouse() {
        System.out.println("出售房屋");
    }

    @Override
    public void cleanHouse() {
        System.out.println("打扫房屋");
    }

    @Override
    public void rentHouse() {
        System.out.println("出租房屋");
    }
}
