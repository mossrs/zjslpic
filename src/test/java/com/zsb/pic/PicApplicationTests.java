package com.zsb.pic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsb.pic.entity.User;
import com.zsb.pic.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
class PicApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

}
