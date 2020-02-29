package com.pw.chain;

import sun.nio.cs.ext.ISCII91;

public abstract class IsEmptyAbstract {
    private IsEmptyAbstract isEmptyJudge = null;

    //获取下一个处理请求的拦截器对象
    public IsEmptyAbstract getIsEmptyJudge() {
        return isEmptyJudge;
    }
    //设置下一个处理请求的拦截器对象
    public void setIsEmptyJudge(IsEmptyAbstract isEmptyJudge) {
        this.isEmptyJudge = isEmptyJudge;
    }

    public abstract boolean isEmpty(String str);
}
