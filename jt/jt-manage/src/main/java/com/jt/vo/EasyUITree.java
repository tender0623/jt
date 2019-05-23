package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by CGB on 2019/4/3.
 */

/**
 * VO对象最终转化为json串 调用get方法 无须序列化
 * 如果当前对象需要远程传输,必须序列化
 */
@Data
@Accessors(chain = true)
public class EasyUITree {
    private Long id;  //节点的id号
    private String text; //节点的名称
    private String state; //节点状态

    public EasyUITree() {
    }

    public EasyUITree(Long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }
}
