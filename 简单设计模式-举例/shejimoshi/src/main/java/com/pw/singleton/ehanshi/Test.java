package com.pw.singleton.ehanshi;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    SingleTon.getInstance(); //只执行一次
                }
            }.start();

        }
    }
}
