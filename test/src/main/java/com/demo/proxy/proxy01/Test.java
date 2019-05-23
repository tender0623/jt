package com.demo.proxy.proxy01;

/**
 * Created by CGB on 2019/4/20.
 */
public class Test {
    public static void main(String[] args){
        UserDao userDao=new UserDaoImpl();
        //UserProxy userProxy=new UserProxy(userDao);
        ProxyFactory proxyFactory =new ProxyFactory(userDao);
       userDao =  proxyFactory.getProxyIntance();
       userDao.save();
    }
}
