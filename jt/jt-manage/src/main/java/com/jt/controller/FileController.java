package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by CGB on 2019/4/8.
 */
@Controller
public class FileController {
    //当文件上传之后,重定向到页面上传页面

    @Autowired
    private FileService fileService;

    @RequestMapping("/file")
    public String fileImage(MultipartFile fileImage ) throws IOException {
        //步骤1.获取文件的名称
        String fileName=fileImage.getOriginalFilename();//表示得到文件的全名,包括后缀
        System.out.println("获取文件名称:"+fileName);

        //2.指定文件的目录(判断文件是否存在)
        String filePath="D:/abc/jt-upload";
        File dirFile=new File(filePath);
        //如果文件夹不存在,创建文件夹
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        //3.实现文件上传
        String realName="D:/abc/jt-upload/"+fileName;
        fileImage.transferTo(new File(realName));
        return "redirect:/file.jsp";
    }

    //实现商品文件上传
    @RequestMapping("/pic/upload")
    @ResponseBody
    public FileVo uploadFile(MultipartFile uploadFile){

        return fileService.upload(uploadFile);
    }
}