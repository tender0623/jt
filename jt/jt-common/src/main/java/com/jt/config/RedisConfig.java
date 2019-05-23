package com.jt.config;

/**
 * Created by CGB on 2019/4/12.
 */

import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;
import redis.clients.util.ShardInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**该配置为配置类,代替之前的配置文件*/
@Configuration//表示配置类 当springboot程序启动时,会加载配置类
//@ImportResource ("classpath:/spring/application-redis.xml")//导入第三方配置文件
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
    //实现redis集群引入
    @Value("${redis.nodes}")
    private String nodes;
    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodeSet=new HashSet<>();
        String[] node = nodes.split(",");
        for (String h_pNode : node) {
            String[] split = h_pNode.split(":");
            int port = Integer.parseInt(split[1]);
            nodeSet.add(new HostAndPort(split[0],port));
        }
        return new JedisCluster(nodeSet);
    }






//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//    @Bean //将方法的返回值对象交给spring容器管理
//    public Jedis jedis(){
//        return new Jedis(host,port);
//    }

//    @Value("${redis.shards}")
//    private String redisShards;
//    @Bean
//    public ShardedJedis shardedJedis(){
//        List<JedisShardInfo> shards=new ArrayList<>();
//        String[] nodes=redisShards.split(",");
//        for (String node : nodes) {
//            String[] host_port = node.split(":");
//            JedisShardInfo info = new JedisShardInfo(host_port[0], host_port[1]);
//            shards.add(info);
//        }
//
//        return new ShardedJedis(shards);
//    }
//    @Value("${redis.node}")
//    private String node;
//    @Value("${redis.masterName}")
//    private String masterName;
//
//    @Bean
//    public JedisSentinelPool jedisSentinelPool(){
//        Set<String> sentinels = new HashSet<>();
//        sentinels.add(node);
//        return new JedisSentinelPool(masterName,sentinels);
//    }
}