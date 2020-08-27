package com.cdfg.wxmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cdfg.wxmall.dao")
@SpringBootApplication
public class WxmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmallApplication.class, args);
    }

}
