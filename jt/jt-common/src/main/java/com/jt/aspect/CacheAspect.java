package com.jt.aspect;

import com.jt.annotation.Cache;
import com.jt.service.RedisService;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by CGB on 2019/4/15.
 */
@Aspect
@Component
public class CacheAspect {
    @Autowired(required = false)
    private JedisCluster jedis;
    @Around(value = "@annotation(cache)")
    private Object around(ProceedingJoinPoint jp,Cache cache) {
        String key=getKey(jp,cache);
        Object object=getObject(jp,cache,key);
        return object;
    }

    private Object getObject(ProceedingJoinPoint jp, Cache cache,String key) {
        Object object=null;
        switch (cache.cachetType()){
            case FIND:
                object=findCache(jp,cache,key);
                break;
            case UPDATE:
                object=updateCache(jp,cache,key);
        }
        return object;
    }

    private Object updateCache(ProceedingJoinPoint jp, Cache cache, String key) {
        Object object=null;
        try {
            jedis.del(key);
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }

    private Object findCache(ProceedingJoinPoint jp, Cache cache, String key) {
        Object object=null;
        String result = jedis.get(key);
        try {
        if (StringUtils.isEmpty(result)){
                object=jp.proceed();
                String json = ObjectMapperUtil.toJSON(object);
                jedis.set(key,json);
                System.out.println("从数据库查找");
            }else {
            MethodSignature methodSignature= (MethodSignature) jp.getSignature();
            Class<?>aClass = methodSignature.getReturnType();
            object = ObjectMapperUtil.toObject(result, aClass);
            System.out.println("从缓存中查找");
        }
        }catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        return object;

    }


    //key的定义,类名,方法名,参数名称,值
    private String getKey(ProceedingJoinPoint jp, Cache cache) {
        String aClass = jp.getTarget().getClass().getName();
        System.out.println(aClass);
        String targetClassname = jp.getSignature().getDeclaringTypeName();
        System.out.println(targetClassname);
        String methodName = jp.getSignature().getName();
        System.out.println(methodName);
        //转化为方法对象
        MethodSignature methodSignature= (MethodSignature) jp.getSignature();
        String[] parameterNames= methodSignature.getParameterNames();//获得参数的名字
        String parameterName = parameterNames[cache.index()];
        System.out.println(parameterName);
        Object arg = jp.getArgs()[cache.index()];
        String key=targetClassname+"."+methodName+"."+parameterName+"."+arg;
        System.out.println(key);
        return key;


    }

}
