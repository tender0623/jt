package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created by CGB on 2019/4/16.
 */
@Service
public class RedisService {
    @Autowired(required = false)
    private JedisSentinelPool pool;

    public String get(String key) {
        Jedis jedis = pool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public void set(String key,String value) {
        Jedis jedis = pool.getResource();
        jedis.set(key,value);
        jedis.close();
    }
    public void del(String key){
        Jedis jedis = pool.getResource();
        jedis.del(key);
        jedis.close();
    }
}
