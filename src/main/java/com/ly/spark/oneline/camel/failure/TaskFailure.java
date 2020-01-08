package com.ly.spark.oneline.camel.failure;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskFailure {

    public void failure() {
        log.error("文件处理失败！");
    }
}
