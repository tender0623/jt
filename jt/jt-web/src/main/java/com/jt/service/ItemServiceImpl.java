package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.HttpClientService;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CGB on 2019/4/17.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpClientService httpClient;

    @Override
    public Item findItemById(Long itemId) {
        String url="http://manage.jt.com/web/item/findItemById/"+itemId;
        String resultJSON = httpClient.doGet(url);
        Item item = ObjectMapperUtil.toObject(resultJSON, Item.class);
        return item;
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        String url="http://manage.jt.com/web/item/findItemDescById/"+itemId;
        String resultJSON = httpClient.doGet(url);
        ItemDesc itemDesc = ObjectMapperUtil.toObject(resultJSON, ItemDesc.class);
        return itemDesc;
    }
}
