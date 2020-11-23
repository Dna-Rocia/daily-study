package com.daily.javabsc.readconfig;

import com.daily.javabsc.readconfig.conf.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class ReadConfigController {

    @Autowired
    private Environment env;

    @Autowired
    private MyConfig myConfig;

    @Value("server.port")
    private String port;

    @GetMapping("/env")
    public String readByEnvironment(){
        return this.env.getProperty("server.port");
    }


    @GetMapping("/val")
    public String readByValue(){
        return port;
    }


    @RequestMapping("/conf")
    public String readMyConfig(){
        return this.myConfig.toString();
    }

}
