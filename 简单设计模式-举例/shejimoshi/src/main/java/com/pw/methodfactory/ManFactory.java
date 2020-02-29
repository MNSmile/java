package com.pw.methodfactory;

public class ManFactory implements Factory{
    @Override
    public Human createHuman() {
        return new Man();
    }
}
