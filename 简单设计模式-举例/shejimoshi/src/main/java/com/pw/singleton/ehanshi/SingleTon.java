package com.pw.singleton.ehanshi;

/**
 * 饿汉式单例模式
 *   线程安全，类加载时就已经实例化了
 */
public class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    private SingleTon(){
        System.out.println("SingleTon");
    }

    public static SingleTon getInstance(){
        return singleTon;
    }
}
