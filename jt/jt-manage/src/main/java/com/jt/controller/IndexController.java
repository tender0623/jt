package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CGB on 2019/4/3.
 */
@Controller
public class IndexController {

    /**
     * 思路:能够接受用户请求参数,实现页面动态跳转
     * restful风格
     *    1.参数必须使用"/"分割,如果有多个参数,则写多个"/"
     *    2.参数必须使用${}包裹,并且命名名称
     *    3.必须添加注解#pathVariable
     *   特点:可以将url中的请求路径信息动态获取数据
     *   要求:传参数时名称需要统一
     *   用途:可以利用restful代替get请求
     *   优点:节省了url编辑的次数
     * @return
     */

    @RequestMapping("/page/{moduleName}")
    public String module(@PathVariable String moduleName){
        return moduleName;
    }

//    @RequestMapping("item-add")
//    public String module(){
//        return "item-add";
//    }

//    @RequestMapping("item-list")
//    public String moduleB(){
//        return "item-list";
//    }
//    @RequestMapping("item-param-list")
//    public String moduleC(){
//        return "item-param-list";
//    }
}
