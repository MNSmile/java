package com.pw.singleton.staticInnerclass;

/**
 * 使用静态内部类实现单例模式
 */
public class SingleTon {
    private SingleTon(){
        System.out.println("SingleTon");
    }

    public static class SingleTonHolder{
        private static SingleTon singleTon = new SingleTon(); //构建一个对象
    }

    public static SingleTon getInstance(){
        return SingleTonHolder.singleTon;
    }
}
