package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIList;
import com.jt.vo.SysResult;

public interface ItemService {

    EasyUIList findItemByPage(Integer page, Integer rows);

    void saveItem(Item item,ItemDesc itemDesc);

    void updateState(Long[] ids, Integer status);

    void updateItem(Item item,ItemDesc itemDesc);

    void deleteItems(Long[] ids);

    ItemDesc findItemDescById(Long itemId);

    Item findItemById(long itemId);
}
