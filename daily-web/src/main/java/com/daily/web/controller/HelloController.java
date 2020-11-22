package com.daily.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private Environment environment;

    /**
     * 只有配置在 application.properties文件，才可以使用environment 读取到配置
     */
    @GetMapping("/ev")
    public String hello(){
        return environment.getProperty("custom.filename");
    }

}
