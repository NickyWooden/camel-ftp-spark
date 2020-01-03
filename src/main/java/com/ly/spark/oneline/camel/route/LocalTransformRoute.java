package com.ly.spark.oneline.camel.route;

import com.ly.spark.oneline.camel.processor.LocationFileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocalTransformRoute extends RouteBuilder {

    @Value("${route.processor.input}")
    private String input;

    @Value("${route.processor.target}")
    private String target;

    @Autowired
    LocationFileProcessor locationFileProcessor;

    @Override
    public void configure() throws Exception {
        //文件处理业务路由
        from(input).process(locationFileProcessor).to(target).log(LoggingLevel.INFO, log, "trans  file ${file:name} complete.");
    }

}