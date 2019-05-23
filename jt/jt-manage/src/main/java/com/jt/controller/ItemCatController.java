package com.jt.controller;

import com.jt.annotation.Cache;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CGB on 2019/4/3.
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    //实现商品分类信息查询
    @RequestMapping("/queryItemName")
    public String findItemCatNameById(Long itemCatId){

        return itemCatService.findItemCatNameById(itemCatId);
    }

    //实现商品分类信息的树形结构展现

    /**
     * defaultValue:如果没有传递参数,则设定默认值
     * name:参数名称
     * required:true/false 是否必须传递参数
     * value:表示参数名称
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @Cache(cachetType = Cache.CACHE_TYPE.FIND)
    public List<EasyUITree> findItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){

        return itemCatService.findItemCatList(parentId);
    }
}