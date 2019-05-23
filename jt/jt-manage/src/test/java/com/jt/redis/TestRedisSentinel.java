package com.jt.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CGB on 2019/4/16.
 */
public class TestRedisSentinel {

    @Test
    public void test01(){
        String msg = new HostAndPort("192.168.234.132", 26379).toString();
        System.out.println("工具API的结果"+msg);
        Set<String> sentinels=new HashSet<>();
        sentinels.add("192.168.234.132:26379");
        JedisSentinelPool pool=new JedisSentinelPool("mymaster",sentinels);
        //只能操作主机
        Jedis jedis = pool.getResource();
        jedis.set("1812","哨兵测试完成");
        System.out.println(jedis.get("1812"));
        jedis.close();
    }
}
