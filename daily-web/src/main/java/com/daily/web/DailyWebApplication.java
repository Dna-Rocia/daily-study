package com.daily.web;

import com.daily.exception.conf.MainBusiListeners;
import com.daily.exception.handle.CustomException;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan(basePackages = {"com.daily.shiro.mapper"})
public class DailyWebApplication {


    //程序可以直接在此启动
    @RequestMapping("/hello")
    public String index(){
        try{
            test("3");
        }catch (CustomException e){
            System.out.println(e.toString());
        }
        return "hello,项目启动成功！！";
    }


    public static void main(String[] args) {
//        SpringApplication.run(DailyWebApplication.class, args);
        SpringApplication springApplication =new SpringApplication(DailyWebApplication.class);
        springApplication.addListeners(new MainBusiListeners());
        springApplication.run(args);
    }

    private void  test(String pick){
        if (!"2".equals(pick)) {
            throw new CustomException("ERROR");
        }
    }

}
