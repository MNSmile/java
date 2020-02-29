package com.pw.simplefactory;

public class HumanTest {
    public static void main(String[] args) {
        Human human = SimpleFactory.makeHuman("2");
        if(human!=null){
            human.say();
        } else {
            System.out.println("no product");
        }
    }
}
