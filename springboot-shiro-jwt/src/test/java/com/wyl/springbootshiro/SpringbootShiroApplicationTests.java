package com.wyl.springbootshiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Test
    void contextLoads() {
        String wuyilong = new SimpleHash(Md5Hash.ALGORITHM_NAME, "123456", ByteSource.Util.bytes("wuyilong"), 1).toBase64();
        System.out.println(wuyilong);

    }

}
