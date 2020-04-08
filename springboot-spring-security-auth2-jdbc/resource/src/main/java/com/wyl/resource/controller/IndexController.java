package com.wyl.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description
 * @Author yilongwu
 * @DATE 2020-04-07 15:26
 * @Version 1.0.0
 **/
@RestController
@Slf4j
public class IndexController {

    @GetMapping("/")
    public void index() {
        log.info("**********index");
    }

    @GetMapping("/view")
    public void view() {
        log.info("*********view");
    }
}
