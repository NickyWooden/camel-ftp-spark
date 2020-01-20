package com.ly.spark.oneline.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class AgentInfo implements Serializable {
    private Integer id;

    private String agentName;

    private String agentPhone;

    private MultipartFile blFile;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone == null ? null : agentPhone.trim();
    }


    public MultipartFile getBlFile() {
        return blFile;
    }

    public void setBlFile(MultipartFile blFile) {
        this.blFile = blFile;
    }

}