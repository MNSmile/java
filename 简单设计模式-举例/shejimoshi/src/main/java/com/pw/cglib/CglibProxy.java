package com.pw.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private LandLord landLord = null;

    public CglibProxy(LandLord landLord) {
        this.landLord = landLord;
    }

    public LandLord createProxy(){
        //创建一个cglib核心处理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(landLord.getClass());//指定创建出来的子类的父类
        enhancer.setCallback(this);//当调用代理类对象中方法的时候委托给实现MethodInterceptor接口的实现类对象的intercept方法处理
        return (LandLord) enhancer.create();//创建具体的代理类对象
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(method.getName().equals("sellHouse")){
            System.out.println("增强功能");
            System.out.println("验证房屋是否有贷款");
            System.out.println("验证房屋中是否刮油户口");
            //当调用代理类对象方法的时候，其实真正要去调用被代理类对象的方法
            Object res = method.invoke(landLord, args);
            System.out.println("收中介费");
            return res;
        } else {
            return method.invoke(landLord, args);
        }
    }
}
