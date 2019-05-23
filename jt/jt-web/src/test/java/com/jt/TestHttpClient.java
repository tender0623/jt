package com.jt;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by CGB on 2019/4/17.
 */
public class TestHttpClient {
    /**
     * 实现步骤:
     * 1.创建HttpClient对象
     * 2.指定url请求地址
     * 3.指定请求的方式 get/post
     *  规则:查询操作使用get
     *       如果涉及数据入库更新.入库 并且数据量大,采用post提交,舍密码操作也用post
     * 4.发起请求,获取返回值数据 此数据一定是json串
     * 5.判断请求是否正确 检测状态码200
     * 6.从返回值中获取数据json/html,之后进行转化对象
     */

    //实现get请求
    @Test
    public void testGet() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url="https://wenku.baidu.com/view/a4bae12c0066f5335a812160.html";
        HttpGet httpGet=new HttpGet(url);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode()==200){
            System.out.println("请求正确返回");
            String result= EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
        }

    }
}
