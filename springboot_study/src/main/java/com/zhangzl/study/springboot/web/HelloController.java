package com.zhangzl.study.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description  : helloword
 * Create  User : zhangzuolong
 * Create  Time : 2017/9/13 10:10
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello world!";
    }
}
