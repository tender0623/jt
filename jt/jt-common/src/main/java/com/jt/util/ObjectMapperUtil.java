package com.jt.util;

/**
 * Created by CGB on 2019/4/12.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**该工具类解决对象转化中的try-catch问题
 * 1.对象转json   to json
 * 2.json转对象  to Object
 * */
public class ObjectMapperUtil {
    //定义成员变量时不允许修改
    //private static final int a=200;

    //是否有线程安全性问题? 不会,我们调用的是方法
    private static final ObjectMapper mapper= new ObjectMapper();
    public static String toJSON(Object obj){
       String json=null;
        try {
         json =mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //转化为运行时异常
            throw new RuntimeException();
        }
        return json;
    }
    //用户传入类型返回该类的对象
    public static <T>T toObject(String json,Class<T> target){
        T t=null;
        try {
             t = mapper.readValue(json, target);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return t;
    }
}
