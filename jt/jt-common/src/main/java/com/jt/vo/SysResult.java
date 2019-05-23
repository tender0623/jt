package com.jt.vo;

/**
 * Created by CGB on 2019/4/4.
 */

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 该类表示页面统一放回信息
 * status 200表示业务处理正确
 *         201表示业务处理失败
 * msg     表示服务端返回提示信息
 * data    表示服务端返回的数据
 */

@Accessors(chain = true)
@Data
public class SysResult {
    private Integer status;
    private String msg;
    private Object data;

    public SysResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SysResult() {
    }
    //表示定义成功的静态方法
    public static SysResult ok(String msg,Object data){

        return new SysResult(200,msg,data);
    }
    //表示定义成功的静态方法
    public static SysResult ok(){

        return new SysResult(200,null,null);
    }
    public static  SysResult ok(Object data){
        return new SysResult(200,null,data);
    }
    //定义一个失败的静态方法
    public static SysResult fail(String msg,Object data){
        return new SysResult(201,msg,data);
    }
    public static SysResult fail(){
        return new SysResult(201,null,null);
    }
}
