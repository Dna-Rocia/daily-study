package com.daily.exception.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomExcImportBeanDefinition.class)
@Documented
public @interface EnableCustomException {
    /**
     * 是否开启
     *
     * @return
     */
    boolean enabled() default true;

}