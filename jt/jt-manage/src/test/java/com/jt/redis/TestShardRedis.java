package com.jt.redis;

/**
 * Created by CGB on 2019/4/15.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Redis分片计数
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestShardRedis {

    @Test
    public void test01(){
        //准备list集合封装多台Redis
        List<JedisShardInfo> shards=new ArrayList<>();
        shards.add(new JedisShardInfo("192.168.234.132",6379));
        shards.add(new JedisShardInfo("192.168.234.132",6380));
        shards.add(new JedisShardInfo("192.168.234.132",6381));
        ShardedJedis jedis=new ShardedJedis(shards);
        //当前操作的是Redis的分片对象
        jedis.set("1812","分片机制成功");
        System.out.println(jedis.get("1812"));
    }
    //测试 spring整合Redis分片
    @Autowired
    private ShardedJedis shardedJedis;
    @Test
    public void test02(){
        shardedJedis.set("1812","分片机制成功");
        System.out.println(shardedJedis.get("1812"));
    }
}
