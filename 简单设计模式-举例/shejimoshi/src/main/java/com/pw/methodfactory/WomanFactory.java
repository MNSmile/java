package com.pw.methodfactory;

public class WomanFactory implements Factory{

    @Override
    public Human createHuman() {
        return new Woman();
    }
}
