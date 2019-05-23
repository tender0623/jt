package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.annotation.Cache;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by CGB on 2019/4/3.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;
    //@Autowired
    //private Jedis jedis;
    @Override
    public String findItemCatNameById(Long itemCatId) {
        ItemCat itemCat = itemCatMapper.selectById(itemCatId);
        return itemCat.getName();
    }


    @Override
    public List<EasyUITree> findItemCatList(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
        //需要放回VO list集合信息,则需要遍历ItemCat
        ArrayList<EasyUITree> list = new ArrayList<>();
        for (ItemCat itemCat : itemCatList) {
            EasyUITree tree = new EasyUITree();
            tree.setId(itemCat.getId()).setText(itemCat.getName());
            String state=itemCat.getIsParent()?"closed":"open";
            tree.setState(state);
            list.add(tree);
        }
        return list;
    }

//    @Override
//    public List<EasyUITree> findItemCatCacheList(Long parentId) {
//        List<EasyUITree> list = new ArrayList<>();
//        String key="ITEM_CAT_"+parentId;
//        String result=jedis.get(key);
//        if (StringUtils.isEmpty(result)){
//            //如果缓存中为空,则取查询数据库
//            list=findItemCatList(parentId);
//            //将数据保存到缓存中
//            String json = ObjectMapperUtil.toJSON(list);
//            jedis.set(key,json);
//            System.out.println("查询数据库");
//        }else{
//            list = ObjectMapperUtil.toObject(result, list.getClass());
//            System.out.println("查询Redis缓存");
//
//        }
//        return list;
//    }

}
