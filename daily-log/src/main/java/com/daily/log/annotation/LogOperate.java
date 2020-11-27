package com.daily.log.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/28
 */

@Target(ElementType.METHOD)//注解注入的合法位置，METHOD作用在方法上
@Retention(RetentionPolicy.RUNTIME) //注解策略。注解在程序的哪个阶段上执行（RUNTIME  注解被编译在文件中，运行时保留在VM）
@Documented //被 javadoc工具记录
public @interface LogOperate {

    String operaModel() default ""; //操作模块

    String operaType() default ""; //操作类型

    String operaDesc() default ""; //操作说明

}
