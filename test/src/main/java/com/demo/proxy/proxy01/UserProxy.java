package com.demo.proxy.proxy01;

/**
 * Created by CGB on 2019/4/20.
 */
public class UserProxy implements UserDao {
    private UserDao userDao;

    public UserProxy(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public void save() {
        System.out.println("123");
        userDao.save();
        System.out.println("456");
    }
}
