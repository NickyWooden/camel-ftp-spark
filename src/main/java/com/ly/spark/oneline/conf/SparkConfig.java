package com.ly.spark.oneline.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Component
@Data
@Validated
@ConfigurationProperties(prefix = "spark")
public class SparkConfig {

    @NotBlank
    private String sparkHome;
    @NotBlank
    private String jobJar;
    @NotBlank
    private String mainMethod;
    @NotBlank
    private String mainArgs;
    private SparkRuntime runtime;

}
