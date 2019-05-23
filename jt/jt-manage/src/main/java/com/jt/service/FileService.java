package com.jt.service;

import com.jt.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by CGB on 2019/4/8.
 */
public interface FileService {
    FileVo upload(MultipartFile uploadFile);
}
