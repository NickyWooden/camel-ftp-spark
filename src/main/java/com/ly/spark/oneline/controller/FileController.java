package com.ly.spark.oneline.controller;

import com.ly.spark.oneline.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class FileController {


    @Autowired
    FtpUtils ftpUtils;


    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public String saveHeaderPic(@RequestParam("blFile") MultipartFile blFile) throws Exception {
        // 文件保存路径
        String filePath = "pic"; //映射的地址

        String filename = blFile.getOriginalFilename();//获取file图片名称

        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println("suffix :" + suffix);
        String uploadFile = ftpUtils.uploadFile(filePath, blFile.getBytes(), UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix);
        return uploadFile;
    }
}
