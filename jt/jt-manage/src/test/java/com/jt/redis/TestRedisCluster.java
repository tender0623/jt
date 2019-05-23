package com.jt.redis;

import org.apache.catalina.Host;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CGB on 2019/4/16.
 */
public class TestRedisCluster {
    @Test
    public void test(){
        Set<HostAndPort> nodes= new HashSet<>();
        nodes.add(new HostAndPort("192.168.234.132",7000));
        nodes.add(new HostAndPort("192.168.234.132",7001));
        nodes.add(new HostAndPort("192.168.234.132",7002));
        nodes.add(new HostAndPort("192.168.234.132",7003));
        nodes.add(new HostAndPort("192.168.234.132",7004));
        nodes.add(new HostAndPort("192.168.234.132",7005));
        nodes.add(new HostAndPort("192.168.234.132",7006));
        nodes.add(new HostAndPort("192.168.234.132",7007));
        nodes.add(new HostAndPort("192.168.234.132",7008));
        JedisCluster jedisCluster=new JedisCluster(nodes);
        jedisCluster.set("1812","集群搭建完毕");
        System.out.println(jedisCluster.get("1812"));
    }

}
