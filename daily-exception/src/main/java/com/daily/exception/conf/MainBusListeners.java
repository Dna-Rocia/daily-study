package com.daily.exception.conf;

import com.daily.exception.utils.SpringContextUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 监听上下文
 */
public class MainBusListeners implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SpringContextUtil.setApplicationContextByMain(event.getApplicationContext());
    }
}
