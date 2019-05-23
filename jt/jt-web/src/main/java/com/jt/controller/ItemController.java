package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CGB on 2019/4/17.
 */
@Controller
@RequestMapping("/items/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    //跳转页面
    @RequestMapping("{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model){
        Item item=itemService.findItemById(itemId);
        model.addAttribute("item",item);
        ItemDesc itemDesc=itemService.findItemDescById(itemId);
        model.addAttribute("itemDesc",itemDesc);
        return "item";
    }
}
