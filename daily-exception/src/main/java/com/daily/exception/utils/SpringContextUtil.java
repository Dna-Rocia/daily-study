package com.daily.exception.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContextByMain(ApplicationContext applicationContext) {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static String getApplicationConfigurationPath() {
        if (applicationContext == null) {
            return null;
        }
        MutablePropertySources mutablePropertySources = ((ConfigurableApplicationContext) applicationContext).getEnvironment().getPropertySources();
        String path = "";
        for (PropertySource source : mutablePropertySources) {
            if (source.getName().contains("applicationConfig")) {
                path = source.getName();
            }
        }
        path = path.split("/")[1];
        path = "/" + path.substring(0, path.length() - 1);
//            System.out.println("监听到的： "+path );
        return path;
    }

}
