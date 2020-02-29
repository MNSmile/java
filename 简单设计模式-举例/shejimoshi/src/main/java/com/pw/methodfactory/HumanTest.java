package com.pw.methodfactory;

import com.pw.simplefactory.SimpleFactory;

public class HumanTest {
    public static void main(String[] args) {
        Human man = new ManFactory().createHuman();
        man.say();
        Human woman = new WomanFactory().createHuman();
        woman.say();
        Human gay = new GayFactory().createHuman();
        gay.say();
    }
}
