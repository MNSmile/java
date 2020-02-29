package com.pw.jdkproxy;

public class Test {
    public static void main(String[] args) {
        HouseManager landLord = new LandLord();
        //通过这个代理类对象来指定生成的代理类对象代理的具体对象
        JDKProxy jdkProxy = new JDKProxy(landLord);
        //创建出来具体的代理类对象
        HouseManager proxy = jdkProxy.createProxy();
        proxy.sellHouse();
        proxy.cleanHouse();
        proxy.rentHouse();
    }
}
