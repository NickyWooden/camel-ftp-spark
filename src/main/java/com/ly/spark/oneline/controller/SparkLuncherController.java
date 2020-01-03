package com.ly.spark.oneline.controller;

import com.ly.spark.oneline.conf.SparkConfig;
import com.ly.spark.oneline.conf.SparkRuntime;
import org.apache.spark.SparkConf;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SparkLuncherController {
    @Autowired
    private SparkConfig sparkConfig;

    @GetMapping(value = "/sparkPI")
    public String submitTast() {
        HashMap env = new HashMap();
        //这两个属性必须设置
        env.put("HADOOP_CONF_DIR", System.getenv().getOrDefault("HADOOP_CONF_DIR", "/usr/local/hadoop/etc/overriterHaoopConf"));
        env.put("JAVA_HOME", System.getenv().getOrDefault("JAVA_HOME", "/usr/local/java/jdk1.8.0_151"));
        //env.put("YARN_CONF_DIR","");
        SparkRuntime runtime = sparkConfig.getRuntime();
        SparkLauncher handle = new SparkLauncher(env)
                .setSparkHome(sparkConfig.getSparkHome())
                .setAppResource(sparkConfig.getJobJar())
                .setMainClass(sparkConfig.getMainMethod())
                .setMaster(runtime.getMaster())
                .setDeployMode(runtime.getDeployMode())
                //设置传入参数
                .addAppArgs(sparkConfig.getMainArgs())
                .setConf("spark.yarn.queue", runtime.getYarnQueue())
                .setConf("spark.app.id", "launcher-job")
                .setConf("spark.driver.memory", runtime.getDriverMemory())
                .setConf("spark.executor.memory", runtime.getExecutorMemory())
                .setConf("spark.executor.instances", runtime.getExecutorInstances())
                .setConf("spark.executor.cores", runtime.getCores())
                .setConf("spark.default.parallelism", String.valueOf(Integer.parseInt(runtime.getCores()) * Integer.parseInt(runtime.getExecutorInstances()) * 3))
                .setConf("spark.driver.allowMultipleContexts", "true")
                .setVerbose(true);


        try {
            Process process = handle.launch();
            InputStreamReaderRunnable inputStreamReaderRunnable = new InputStreamReaderRunnable(process.getInputStream(), "input");
            Thread inputThread = new Thread(inputStreamReaderRunnable, "LogStreamReader input");
            inputThread.start();

            InputStreamReaderRunnable errorStreamReaderRunnable = new InputStreamReaderRunnable(process.getErrorStream(), "error");
            Thread errorThread = new Thread(errorStreamReaderRunnable, "LogStreamReader error");
            errorThread.start();

            System.out.println("Waiting for finish...");
            int exitCode = process.waitFor();
            System.out.println("Finished! Exit code:" + exitCode);
            return "status: " + exitCode;

        } catch (Exception e) {
            e.printStackTrace();
            return "status: " + 1;
        }

    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "this is hello page!";
    }

}
