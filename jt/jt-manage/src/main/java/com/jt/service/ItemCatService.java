package com.jt.service;

import com.jt.vo.EasyUITree;

import java.util.List;

/**
 * Created by CGB on 2019/4/3.
 */
public interface ItemCatService {

    String findItemCatNameById(Long itemCatId);

    List<EasyUITree> findItemCatList(Long parentId);

   // List<EasyUITree> findItemCatCacheList(Long parentId);
}
