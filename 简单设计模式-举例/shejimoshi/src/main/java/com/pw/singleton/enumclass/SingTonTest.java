package com.pw.singleton.enumclass;

public class SingTonTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    SingleTon singleTon = SingleTon.SINGLETON;
                }
            }.start();
        }
    }
}
