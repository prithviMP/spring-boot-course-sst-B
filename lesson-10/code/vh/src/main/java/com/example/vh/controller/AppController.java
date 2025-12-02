package com.example.vh.controller;


import com.example.vh.config.AppInfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppController {
    @Autowired
    private AppInfoConfig appInfoConfig;

    @GetMapping("/info")
    public Map<String,Object> getAppInfo(){
        return Map.of(
                "name", appInfoConfig.getName(),
                "version", appInfoConfig.getVersion(),
                "description", appInfoConfig.getDescription(),
                "maintainer", Map.of(
                        "name", appInfoConfig.getMaintainer().getName(),
                        "email", appInfoConfig.getMaintainer().getEmail()
                )
        );
    }
}
