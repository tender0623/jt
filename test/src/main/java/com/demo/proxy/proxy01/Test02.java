package com.demo.proxy.proxy01;

/**
 * Created by CGB on 2019/4/20.
 */
public class Test02 {
    public static void main(String[] args){
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.getClass());
        UserDao o = (UserDao) new ProxyFactory(userDao).getProxyIntance();
        System.out.println(o.getClass());
       o.save();
    }



}
