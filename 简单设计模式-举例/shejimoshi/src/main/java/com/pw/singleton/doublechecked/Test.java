package com.pw.singleton.doublechecked;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    SingleTon.getInstance();
                }
            }.start();
        }
    }
}
