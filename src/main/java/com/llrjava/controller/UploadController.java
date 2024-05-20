package com.llrjava.controller;

import com.llrjava.pojo.Result;
import com.llrjava.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    //本地存储
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传: {}, {}, {}",username,age,image);
//
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构建唯一的文件名(不能重复)  --uuid(同样唯一识别码)
//        //先获取文件名尾缀
//        int index=originalFilename.lastIndexOf(".");
//        String extName = originalFilename.substring(index);
//
//        //将uuid和尾缀拼接到一起变成唯一的文件名
//        String nweFileName = UUID.randomUUID().toString()+extName;
//        log.info("新的文件名:{}",nweFileName);
//
//        //将文件存储在服务器的磁盘目录中  D:\llrimages
//        image.transferTo(new File("D:\\llrimages\\"+nweFileName));
//
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}",image.getOriginalFilename());

        //调用阿里云oss工具类进行文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url是 {}",url);

        return Result.success(url);

    }

}
