package com.pw.chain;

public class IsEmpty extends IsEmptyAbstract{

    @Override
    public boolean isEmpty(String str) {
        if(str.equals("")){ //处理为空的情况
            System.out.println("处理为空的情况");
            return true;
        } else { //不能处理不为空的情况，交个下一个处理
            System.out.println("不能处理不为空的情况，交个下一个处理");
            getIsEmptyJudge().isEmpty(str);
            return false;
        }
    }
}
