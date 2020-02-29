package com.pw.cglib;

public class Test {
    public static void main(String[] args) {
        LandLord landLord = new LandLord();
        CglibProxy cglibProxy = new CglibProxy(landLord);
        LandLord proxy = cglibProxy.createProxy();
        proxy.cleanHouse();
        proxy.rentHouse();
        proxy.sellHouse();
    }
}
