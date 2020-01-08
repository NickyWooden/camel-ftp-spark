package com.ly.spark.oneline.camel.processor;

import com.ly.spark.oneline.utils.FtpOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class LocationFileProcessor implements Processor {


    @Value("${ftp.download.local}")
    private String fileDir;
    @Value("${ftp.upload.status}")
    private String upFailure;
    @Value("${ftp.upload.server}")
    private String upftpServer;
    @Value("${ftp.upload.local}")
    private String uploadLocation;

    @Override
    public void process(Exchange exchange) throws Exception {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        String fileName = inFileMessage.getGenericFile().getFileName();//文件名
        String splitTag = /*File.separator*/ "/";//系统文件分隔符
        String filePath = fileDir + splitTag + fileName;
        log.info("current handel file: {}", fileDir + splitTag + fileName);//文件的绝对路径
        log.info("file: {}", filePath.substring(5));
        try {
            File file = new File(filePath.substring(5));
            InputStream inputStream = new FileInputStream(file);
            boolean flag = FtpOperation.writeToFile(inputStream, "d:/test/ftp/spark/liyuan.txt");
            log.info("文件写入成功？： {}", flag);
            if (!flag) {
                log.error("文件解析失败!");
                throw new IOException("文件解析失败！");
            }
            if (fileName.equals("ly.json")) {
                throw new RuntimeException("文件处理失败！");
            }
            FileWriter fwriter = new FileWriter("d:/test/ftp/status/_SUCCESS.txt");
            fwriter.write("success: true");
            fwriter.close();
        } catch (Exception e) {
            FileWriter fwriter = new FileWriter("d:/test/ftp/status/_FAILURE.txt");
            fwriter.write("success: false," + e.toString());
            fwriter.close();
            throw e;
        }

    }


}