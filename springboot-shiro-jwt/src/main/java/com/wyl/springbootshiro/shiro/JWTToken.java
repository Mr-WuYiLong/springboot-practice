package com.wyl.springbootshiro.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName JWTToken
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-13 15:28
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
public class JWTToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
