package com.wyl.redis.exception;

import com.wyl.common.bean.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/12 10:08
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseData businessException(BusinessException exception ) {
        return ResponseData.create(-1,exception.getMessage());
    }
}
