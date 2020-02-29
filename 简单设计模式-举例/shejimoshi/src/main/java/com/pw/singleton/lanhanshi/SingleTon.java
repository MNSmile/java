package com.pw.singleton.lanhanshi;

/**
 * 懒汉式单例模式
 */
public class SingleTon {
    private static SingleTon singleTon = null;
    private SingleTon(){
        System.out.println("SingleTon");
    }

    public static SingleTon getInstance(){
        if(singleTon==null){
            singleTon = new SingleTon(); //调用时才实例化，存在不安全性
        }
        return singleTon;
    }
}
