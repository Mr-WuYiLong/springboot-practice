package com.wyl.dubbo;

import com.wyl.dubbo.service.UserService;
import com.wyl.dubbo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class ConsumerApplicationTests {

    @Reference
    private UserService userService;

    @Test
    void contextLoads() {
        List<User> userList = userService.getUserList();
        log.info("*******{}", userList);
    }

}
