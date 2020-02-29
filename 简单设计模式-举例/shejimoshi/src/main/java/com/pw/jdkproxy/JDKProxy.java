package com.pw.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类，帮我们生成一个代理类对象
 */
public class JDKProxy implements InvocationHandler {
    private HouseManager houseManager = null;

    public JDKProxy(HouseManager houseManager) {
        this.houseManager = houseManager;
    }

    /**
     * 创建一个代理对象, 此对象具备
     */
    public HouseManager createProxy(){
        /**
         * 第一个参数，类加载器
         * 第二个参数，指定创建出来的代理类对象要实现那些方法
         * 第三个参数，调用处理器InvocationHandle,也就是说当我们去调用代理类对象的方法时就会交给实现InvocationHandle接口的实现类对象来进行处理
         */
        return  (HouseManager)Proxy.newProxyInstance(houseManager.getClass().getClassLoader(), houseManager.getClass().getInterfaces(), this);
    }

    /**
     *回调方法
     * @param proxy 创建出来的代理类对象
     * @param method 调用代理类对象的具体方法
     * @param args 调用代理类对象的具体方法中的实参
     * @return 方法执行后抛出的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("sellHouse")){
            System.out.println("增强功能");
            System.out.println("验证房屋是否有贷款");
            System.out.println("验证房屋中是否刮油户口");
            //当调用代理类对象方法的时候，其实真正要去调用被代理类对象的方法
            Object res = method.invoke(houseManager, args);
            System.out.println("收中介费");
            return res;
        } else {
            return method.invoke(houseManager, args);
        }
    }
}
