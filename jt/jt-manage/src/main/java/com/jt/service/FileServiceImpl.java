package com.jt.service;

import com.jt.vo.FileVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by CGB on 2019/4/8.
 */

@Service //默认对象是单利的,所以不要修改成员变量
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
    @Value("${image.dirPath}")
    String localPath;
    @Value("${image.urlPath}")
    String urlPath;
    @Override
    public FileVo upload(MultipartFile uploadFile) {
        FileVo fileVo = new FileVo();
        //获取文件名称
        String fileName = uploadFile.getOriginalFilename();
        fileName = fileName.toLowerCase();//将文件名称统一为小写,方便以后判断
        //利用正则表达式判断 ^开始.$表示结束,"."除了回车换行之外的任意单个字符
        //+至少一个,"*" 0个或者多个
        if (!fileName.matches("^.+\\.(png|jpg|gif)$")){
            fileVo.setError(1);
            return fileVo;
        }
        //判断是否为恶意类程序
        try {
            BufferedImage image = ImageIO.read(uploadFile.getInputStream());
            //获取宽度 和高度
            int width = image.getWidth();
            int height = image.getHeight();
            //判断属性是否为0
            if (width==0||height==0){
                fileVo.setError(1);
                return fileVo;
            }
            //根据时间 生成文件夹
            String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String localDir=localPath+dateDir;
            File file = new File(localDir);
            if (!file.exists()){
                file.mkdirs();
            }
            //表示文件夹已经生成
            //6.防止文件名称重复

            //6.1生成UUID
            String uuidName=UUID.randomUUID().toString().replace("-","");
            //6.2获取文件类型 进行拼接
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String realName=uuidName+fileType;
            //6.3实现文件上传
            File realFile = new File(localDir + "/" + realName);
            uploadFile.transferTo(realFile);
            fileVo.setWidth(width);
            fileVo.setHeight(height);
            //设置图片的访问路径
            String realUrlPath=urlPath+dateDir+"/"+realName;
            fileVo.setUrl(realUrlPath);
        } catch (IOException e) {
            e.printStackTrace();
            //表示为恶意的程序
            fileVo.setError(1);
            return fileVo;
        }
        return fileVo;
    }
}