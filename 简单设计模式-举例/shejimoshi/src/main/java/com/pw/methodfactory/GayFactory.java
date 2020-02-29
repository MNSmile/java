package com.pw.methodfactory;

public class GayFactory implements Factory{
    @Override
    public Human createHuman() {
        return new Gay();
    }
}
