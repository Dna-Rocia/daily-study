package com.daily.exception.annotation;

import com.daily.exception.handle.CustomExceptionHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class CustomExcImportBeanDefinition implements ImportBeanDefinitionRegistrar {
    /**
     * implements HandlerInterceptor
     *
     * @param annotationMetadata     当前类的注解信息
     * @param beanDefinitionRegistry BeanDefinition注册类
     *                               把所有需要添加到容器中的bean；
     *                               调用BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        String clazzName = EnableCustomException.class.getName();

        //是否含有@EnableCustomException注解
        if (annotationMetadata.isAnnotated(clazzName)) {
            //获取该注解上面的所有属性，然后封装成一个map
            MultiValueMap<String, Object> attributes = annotationMetadata.getAllAnnotationAttributes(clazzName);
            if (attributes.get("enabled").get(0) == Boolean.TRUE) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(CustomExceptionHandler.class);
                beanDefinitionRegistry.registerBeanDefinition(CustomExceptionHandler.class.getName(), beanDefinitionBuilder.getBeanDefinition());
            }
        }

    }
}
