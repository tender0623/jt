package com.demo.proxy.proxy01;

/**
 * Created by CGB on 2019/4/20.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口,但是需要指定接口类型
 */
public class ProxyFactory {
    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target=target;
    }
    //给目标对象生成代理对象
    public <T>T getProxyIntance(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces()
        , new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务");
                        //执行目标方法
                        Object invoke = method.invoke(target, args);
                        System.out.println("关闭事务");
                        return invoke;
                    }
                });
    }
}
