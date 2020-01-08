package com.ly.spark.oneline.camel.route;

import com.ly.spark.oneline.camel.processor.LocationFileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Slf4j
public class DownloadRouteDemo extends RouteBuilder {


    @Value("${ftp.download.server}")
    private String sftpServer;
    @Value("${ftp.download.local}")
    private String downloadLocation;
    @Value("${ftp.download.failure}")
    private String failPath;
    @Value("${ftp.upload.server}")
    private String upftpServer;
    @Value("${ftp.upload.local}")
    private String uploadLocation;
    @Value("${ftp.upload.upload-status}")
    private String uploadStatus;
    @Autowired
    LocationFileProcessor locationFileProcessor;

    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel(failPath));
        from(sftpServer)
                .to(downloadLocation)
                .log(LoggingLevel.INFO, log, "download file ${file:name} complete.");
        from(downloadLocation)
                .process(locationFileProcessor)
//                .to("direct:upload")
                .log(LoggingLevel.INFO, log, "handel file ${file:name} complete.");
        from(uploadLocation)
                .to(upftpServer)
                .log(LoggingLevel.INFO, log, "Upload file ${file:name} complete.");
        from(uploadStatus)
                .to(upftpServer)
                .log(LoggingLevel.INFO, log, "Upload status file ${file:name} complete.");
    }

}