package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by CGB on 2019/4/17.
 */

/**
 * 如果程序启动报数据源相关的错误,则springboot程序启动时,会根据pom.xml文件中的数据源的jar包
 * 加载相关配置,但是jt-web项目只是引用jar包,当时不需要连接数据源,所以在yml配置中没有该配置,导致保错
 * 解决策略:1.在yml文件中添加数据源配置
 * 2.让springboot容器启动时,不加载数据源的配置
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//排除数据源
public class SpringBootRun02 {
    public static void main(String[] args){
        SpringApplication.run(SpringBootRun02.class,args);
    }
}
