package com.pw.chain;

public class IsNull extends IsEmptyAbstract{

    @Override
    public boolean isEmpty(String str) {
        if(str==null){ //处理为null的情况
            System.out.println("处理为null的情况");
            return true;
        } else { //不能处理不为空的情况，交个下一个处理
            System.out.println("不为null处理不了，交给下一任处理");
            return getIsEmptyJudge().isEmpty(str);
        }
    }
}
