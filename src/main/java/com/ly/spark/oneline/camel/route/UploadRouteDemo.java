package com.ly.spark.oneline.camel.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UploadRouteDemo extends RouteBuilder {


    @Value("${ftp.upload.server}")
    private String sftpServer;
    @Value("${ftp.upload.local}")
    private String downloadLocation;

    @Override
    public void configure() throws Exception {
        from(downloadLocation).to(sftpServer).log(LoggingLevel.INFO, log, "Upload file ${file:name} complete.");
    }

}