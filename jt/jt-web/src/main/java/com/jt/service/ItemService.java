package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

/**
 * Created by CGB on 2019/4/17.
 */
public interface ItemService {
    Item findItemById(Long itemId);

    ItemDesc findItemDescById(Long itemId);
}
