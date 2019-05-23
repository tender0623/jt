package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;

/**
 * Created by CGB on 2019/4/19.
 */

@Service
public class DubboUserServiceImpl implements DubboUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public void saveUser(User user) {
        //String solt="cn.tedu.tarena";

        String md5Pass= DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass).setEmail(user.getPhone()).setCreated(new Date()).setUpdated(user.getCreated());
        userMapper.insert(user);
}

    /**
     * 1.根据用户名和密码查询数据库
     * 2.生成秘钥token串,MD5加密
     * 3.把对象装换为json串
     * 4.将数据保存到redis
     * @param user
     * @return
     */
    @Override
    public String findUserByUP(User user) {
        //1.将密码加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        QueryWrapper<User> wrapper =new QueryWrapper();
        wrapper.eq("username",user.getUsername()).eq("password",md5Pass);
        User userDB = userMapper.selectOne(wrapper);
        if (userDB==null){
            return null;
        }
        String token="JT_TICKET_"+System.currentTimeMillis()+user.getUsername();
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        userDB.setPassword("杰哥最帅");
        String json = ObjectMapperUtil.toJSON(userDB);
        jedisCluster.setex(token,7*24*3600,json);
        return token;
    }
}
