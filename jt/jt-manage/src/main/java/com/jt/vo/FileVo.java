package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by CGB on 2019/4/8.
 */

@Data
@Accessors(chain = true)
public class FileVo {

    //文件上传后的VO对象
    private Integer error=0;
    private String url;
    private Integer width;
    private Integer height;

    public FileVo(Integer error, String url, Integer width, Integer height) {
        this.error = error;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public FileVo() {
    }
}
