package com.zsb.pic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author z's'b
 */
@SpringBootApplication
@MapperScan({"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.zsb.pic.mapper"})
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.zsb.pic"})
public class PicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicApplication.class, args);
    }
}
