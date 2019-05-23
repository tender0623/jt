package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CGB on 2019/4/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {



    @Reference(timeout = 3000,check = false)
    private DubboUserService userService;

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 实现用户新增
     * */
    @ResponseBody
    @RequestMapping("/doRegister")
    public SysResult saveUser(User user){
        try {
            userService.saveUser(user);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.fail();
        }
    }


    //实现用户的单点登录

    /**
     * 返回值要求:
     * 如过用户名或密码错误,则token为null
     * 如果用户填写正确.token中有数据
     * 业务流程:
     *  1.判断数据是否有限
     *  2.如果有效则应该将数据保存到cookie中
     *
     *
     *  关于cookie的补充知识
     *
     * @param user
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult login(User user, HttpServletResponse response){
        try {
            String token=userService.findUserByUP(user);
            //下行代表用户名和密码正确
            if (!StringUtils.isEmpty(token)){
                Cookie cookie = new Cookie("JT_TICKET", token);
                cookie.setMaxAge(24*3600*7);
                cookie.setPath("/");//cookie的权限.一般默认写"/"
                response.addCookie(cookie);
                return SysResult.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.fail();
    }

    /**
     * 现获取用户浏览器端的cookie的数据
     * 根据token数据删除redis
     * 删除cookie
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        String token=null;
        //1.获取cookie数据
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())){
                token=cookie.getValue();
                break;
            }
        }
        //删除redis数据
        jedisCluster.del(token);
        //删除cookie 0:表示立即删除  -1:关闭会话时删除
        Cookie cookie = new Cookie("JT_TICKET", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/index.html";
    }


    //实现页面通用跳转
    @RequestMapping("/{moduleName}")
    public String moduleName(@PathVariable String moduleName){

        return moduleName;
    }
}
