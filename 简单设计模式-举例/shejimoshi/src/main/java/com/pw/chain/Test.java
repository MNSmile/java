package com.pw.chain;

public class Test {
    public static void main(String[] args) {
        IsEmptyAbstract isEmpty = new IsEmpty();
        IsEmptyAbstract isNull = new IsNull();
        isNull.setIsEmptyJudge(isEmpty); //isNull下一个处理对象为isEmpty
        boolean flag = isNull.isEmpty("");
        System.out.println(flag);
    }
}
