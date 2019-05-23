package com.jt.service;

import com.jt.pojo.User;

/**
 * Created by CGB on 2019/4/19.
 */
public interface DubboUserService {
    void saveUser(User user);

    //dubbo接口定义,实现用户单点登录
    String findUserByUP(User user);
}
