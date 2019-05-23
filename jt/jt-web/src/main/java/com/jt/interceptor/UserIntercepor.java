package com.jt.interceptor;

/**
 * Created by CGB on 2019/4/23.
 */

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 核心：获取用户的访问数据、路径request、response
 */
@Component
public class UserIntercepor implements HandlerInterceptor{


    //如果没有登录，则跳转用户登录页面 如果登录则放行
    @Autowired
    private JedisCluster jedisCluster;

    /**
     *用户拦截器实现步骤：
     *      1.首先后去用户的cookie数据
     *      2.判断用户是否已经登录，如有用户没有登录，则重定向倒要用户登录页面
     *          如果用户已经登录，则判断redis中是否有数据
     *                             有：表示用户之前登录成功，予以放行
     *                             无：表示用户登录失败，之后重定向到登录页面
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean true 放行。 false 拦截+重定向
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=null;
        //1.获取cookie数据
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())){
                token=cookie.getValue();
                break;
            }
        }
        if (!StringUtils.isEmpty(token)){
            //判断redis中是否有数据
            String userJson = jedisCluster.get(token);
            if (!StringUtils.isEmpty(userJson)){
                User user = ObjectMapperUtil.toObject(userJson, User.class);
               // request.setAttribute("JT_USER",user);
                UserThreadLocal.set(user);
                System.out.println("拦截器启动,传入threadLocal");
                return true;
            }
        }
        //如果程序执行到这里，表示用户一定没有登录
        response.sendRedirect("/user/login.html");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("调用完成,清除数据");
        UserThreadLocal.close();
    }
}
