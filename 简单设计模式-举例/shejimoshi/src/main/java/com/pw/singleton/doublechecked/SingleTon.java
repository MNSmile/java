package com.pw.singleton.doublechecked;

/**
 * 双重检测单例模式，减少是否获取对象锁的判断
 */
public class SingleTon {
    private static SingleTon singleTon = null;
    private SingleTon(){
        System.out.println("SingleTon");
    }

    public static SingleTon getInstance(){
        //第一次检测
        if (singleTon == null) {
            //第二次检测
            synchronized(SingleTon.class) {
               singleTon = new SingleTon();
            }
        }
        return singleTon;
    }
}
  