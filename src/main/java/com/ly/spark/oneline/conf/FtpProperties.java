package com.ly.spark.oneline.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.ftp")
public class FtpProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String uploadPath;
    private String downloadPath;


}