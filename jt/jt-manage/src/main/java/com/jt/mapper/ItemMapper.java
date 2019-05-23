package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{
    /**
     * 关于mybatis取值传参数问题
     * 规定:mybatis 不允许多值传参,只能讲多值转化为单值
     * 1. 封装为pojo对象
     * 2. 封装为map集合
     * 3. 封装为array/list
     * @param start
     * @param rows
     * @return
     */
    List<Item> findItemListByPage(@Param("start") Integer start,
                                  @Param("rows") Integer rows);


}
