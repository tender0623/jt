package com.demo.proxy.proxy02;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by CGB on 2019/4/20.
 */
public class CglibProxyFactory {
    public static Object newProxy(Object target){
        //1.创建enHancer对象()
        Enhancer e = new Enhancer();
        e.setSuperclass(target.getClass());
        e.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("search begin...");
               // Object result = method.invoke(target, objects);
                Object result = methodProxy.invokeSuper(o,objects);
                System.out.println("search end....");
                return result;
            }
        });
        Object proxy = e.create();
        return proxy;
    }
    public static void main(String[] args){
        DefaultSearchService service = new DefaultSearchService();
        DefaultSearchService proxy = (DefaultSearchService) CglibProxyFactory.newProxy(service);
        proxy.search("tmooc");
    }
}
