package com.daily.web;

import com.daily.exception.conf.InitExceptionListener;
import com.daily.exception.handle.CustomException;
import com.daily.exception.utils.Response;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan(basePackages = {"com.daily.shiro.mapper"})
public class DailyWebApplication {


    //程序可以直接在此启动
    @RequestMapping("/hello")
    public Response<String> index() {
        try {
            test("3");
        } catch (CustomException e) {
            System.out.println(e.toString());
            return Response.buildFailResponse(e);
        }
        return Response.buildSuccessResponse("hello,项目启动成功！！");
    }


// 在项目启动的时候，对异常监听进行初始化 方式一：
//SpringApplication springApplication =new SpringApplication(DailyWebApplication.class);
//springApplication.addListeners(new InitExceptionListener());
//springApplication.run(args);

    public static void main(String[] args) {
        SpringApplication.run(DailyWebApplication.class, args);
//        SpringApplication springApplication =new SpringApplication(DailyWebApplication.class);
//        springApplication.addListeners(new InitExceptionListener());
//        springApplication.run(args);
    }

    private void test(String pick) {
        if (!"2".equals(pick)) {
            throw new CustomException("ERROR");
        }
    }

}
