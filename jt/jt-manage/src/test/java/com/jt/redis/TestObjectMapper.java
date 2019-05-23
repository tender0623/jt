package com.jt.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemCat;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by CGB on 2019/4/12.
 */
public class TestObjectMapper {

    //测试一:将Java对象装换为JSON串
    @Test
    public void testToJson() throws IOException {
        ObjectMapper mapper =new ObjectMapper();
        ItemCat itemCat =new ItemCat();
        itemCat.setId(1000L).setName("测试").setParentId(2000L);
        //将对象转化为json串 必须调用对象身上的get/set方法
        String json = mapper.writeValueAsString(itemCat);
        System.out.println(json);
        //将json转化为对象
        ItemCat cat = mapper.readValue(json, ItemCat.class);
        System.out.println(cat);

    }
    @Test
    public void testlistToJson() throws IOException {
        ArrayList<ItemCat> list = new ArrayList<>();
        ItemCat itemCat = new ItemCat();
        for (int i=0;i<5;i++) {
            itemCat.setId(1000L+i).setName("测试"+i).setParentId(2000L+i);
            list.add(itemCat);
        }
        ObjectMapper mapper =new ObjectMapper();

        //将对象转化为json串 必须调用对象身上的get/set方法
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
        //将json转化为对象
        ArrayList value = mapper.readValue(json, list.getClass());
        System.out.println(value);

    }
}
