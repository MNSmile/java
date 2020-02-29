package com.pw.cglib;


/**
 * 具体的被代理对象
 */
public class LandLord{

    public void sellHouse() {
        System.out.println("出售房屋");
    }


    public void cleanHouse() {
        System.out.println("打扫房屋");
    }


    public void rentHouse() {
        System.out.println("出租房屋");
    }
}
