package com.pw.simplefactory;

public class SimpleFactory {
    public static Human makeHuman(String type){
        if(type.equals("1")){
            return new Man();
        } else if(type.equals("2")){
            return new Woman();
        } else {
            return null;
        }
    }
}
