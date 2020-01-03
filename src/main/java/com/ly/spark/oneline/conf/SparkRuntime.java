package com.ly.spark.oneline.conf;

import lombok.Data;

@Data
public class SparkRuntime {
    private String master;
    private String deployMode;
    private String yarnQueue;
    private String cores;
    private String executorInstances;
    private String executorMemory;
    private String driverMemory;
}
