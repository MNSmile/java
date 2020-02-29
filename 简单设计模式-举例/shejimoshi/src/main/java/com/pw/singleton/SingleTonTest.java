package com.pw.singleton;

import com.pw.singleton.staticInnerclass.SingleTon;

public class SingleTonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            new Thread(){
                public void run(){
                    SingleTon.getInstance();
                }
            }.start();
        }
    }
}
