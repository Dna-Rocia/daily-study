package com.daily.javabsc.readconfig.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "daily")
@Configuration
public class MyConfig {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyConfig{" +
                "name='" + name + '\'' +
                '}';
    }
}
