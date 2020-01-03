package com.ly.spark.oneline.camel.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DownloadRouteDemo extends RouteBuilder {


    @Value("${ftp.server.info}")
    private String sftpServer;
    @Value("${ftp.local.dir}")
    private String downloadLocation;

    @Override
    public void configure() throws Exception {
        from(sftpServer).to(downloadLocation).log(LoggingLevel.INFO, log, "Downloaded file ${file:name} complete.");
    }

}