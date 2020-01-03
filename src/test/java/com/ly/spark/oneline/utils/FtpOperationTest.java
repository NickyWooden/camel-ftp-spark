package com.ly.spark.oneline.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FtpOperationTest {
    @Autowired
    FtpOperation ftpOperation;


    //    @Test
    public void uploadToFtp() {
        try {
            File file = new File("/test4.txt");
            InputStream inputStream = new FileInputStream(file);
            boolean success = ftpOperation.uploadToFtp(inputStream, "/output/test-out.txt", false);
            System.out.println("file upload success: " + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadFile() {
        try {
            boolean success = ftpOperation.downloadFile("/input/test4.txt", "/test-in.txt");
            System.out.println("download success: " + success);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}