package com.pw.singleton.enumclass;

/**
 * 枚举类本身就是线程安全的
 */
public enum  SingleTon {
    SINGLETON;
    private SingleTon(){
        System.out.println("singleTon");
    }
}
