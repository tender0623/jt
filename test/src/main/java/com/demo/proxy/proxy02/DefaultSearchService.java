package com.demo.proxy.proxy02;

/**
 * Created by CGB on 2019/4/20.
 */
public class DefaultSearchService {
    public Object search(String key){
        System.out.println("search.....");
        return "result by"+key;
    }
}
