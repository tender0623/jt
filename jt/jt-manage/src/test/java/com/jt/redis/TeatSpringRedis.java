package com.jt.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * Created by CGB on 2019/4/12.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TeatSpringRedis {

    @Autowired
    private Jedis jedis;

    @Test
    public void testSet(){
        jedis.set("1812","spring整合Redis成功");
        System.out.println(jedis.get("1812"));
    }
}
