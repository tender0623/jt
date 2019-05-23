package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by CGB on 2019/4/3.
 */
@TableName("tb_item_desc")
@Accessors(chain = true)
@Data
public class ItemDesc extends BasePojo {
    @TableId  //只标识主键
    private Long itemId;
    private String itemDesc;
}
