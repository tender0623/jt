package com.jt.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by CGB on 2019/4/12.
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class TestRedis {

    //要从容器中获取数据时,才使用spring的测试方法

    //1.实现字符创操作
    @Test
    public void testString() throws InterruptedException {
        Jedis jedis =new Jedis("192.168.234.132",6379);
        jedis.set("1812","tomcat猫");
        System.out.println(jedis.get("1812"));
        jedis.del("aa");
        //设定超时时间
        jedis.expire("1812",30);
        Thread.sleep(2000);
        System.out.println("key还能存活"+jedis.ttl("1812"));
    }

    @Test
    public void testHash(){
        Jedis jedis = new Jedis("192.168.234.132", 6379);
        jedis.hset("user","username","abc");
        Boolean flag = jedis.hexists("user", "username");
        System.out.println(flag);
        Map<String, String> map = jedis.hgetAll("user");
        System.out.println(map);
        List<String> list = jedis.hvals("user");
        System.out.println(list);
        Set<String> set = jedis.hkeys("user");
        System.out.println(set);
    }
    @Test
    public void testList(){
        Jedis jedis = new Jedis("192.168.234.132", 6379);
        jedis.lpush("list","1","2","3","4","5");
        String list = jedis.rpop("list");
        System.out.println(list);
    }
}
