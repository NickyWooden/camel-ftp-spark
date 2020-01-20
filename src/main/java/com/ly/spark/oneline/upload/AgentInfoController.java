package com.ly.spark.oneline.upload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AgentInfoController {

    @Autowired
    private AgentServiceImpl agentService;

    @PostMapping("saveAgent")
    public String saveAgent(AgentInfo agentInfo) throws Exception {
        return agentService.saveAgent(agentInfo);
    }
}