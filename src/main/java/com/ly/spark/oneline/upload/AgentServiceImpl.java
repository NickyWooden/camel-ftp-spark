package com.ly.spark.oneline.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AgentServiceImpl {

    @Value("${upload.dir}")
    private String uploadDir;

    @Transactional
    public String saveAgent(AgentInfo agentInfo) throws Exception {

        MultipartFile blFile = agentInfo.getBlFile();
        if (!blFile.isEmpty()) {
            String oldFileName = blFile.getOriginalFilename();
//            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload/bl";
            String path = uploadDir;
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File file = new File(path, newFileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            blFile.transferTo(file);
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }
}