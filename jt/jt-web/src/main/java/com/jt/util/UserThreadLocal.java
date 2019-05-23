package com.jt.util;

/**
 * Created by CGB on 2019/4/23.
 */

import com.jt.pojo.User;

/***
 * 带对象保存的是用户信息
 */
public class UserThreadLocal {

    //jvm直接创建
    private static ThreadLocal<User> thread = new ThreadLocal<>();

    public static void set(User user) {
        thread.set(user);
    }

    public static User get() {
        return thread.get();
    }

    //使用thread时必须注意内存泄漏
    public static void close(){
        thread.remove();
    }
}