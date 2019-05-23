package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CGB on 2019/4/8.
 */
@RestController
public class TestController {

    @RequestMapping("/msg")
    public String testMsg(){

        return "我是真的Tomcat8093,第一个是假的";
    }
}
