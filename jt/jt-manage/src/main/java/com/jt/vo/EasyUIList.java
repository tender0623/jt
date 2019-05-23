package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by CGB on 2019/4/3.
 */
@Accessors(chain = true)
@Data
public class EasyUIList {
    private Integer total;//记录总数
    private List<?> rows; //保存商品信息

    public EasyUIList(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIList() {
    }
}
