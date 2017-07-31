package com.zhangzl.study.spring.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description  : 观众
 * Create  User : zhangzuolong
 * Create  Time : 2017/7/31 14:41
 */
@Component
@Aspect
public class Audience {

    @Pointcut("execution( * com.zhangzl.study.spring.aop.Performance.*(..))")
    public void performance(){
    }

    /**
     * @Before 执行之前通知
     * 关闭手机
     */
    @Before("performance()")
    public void silenceCellPhone(){
        System.out.println("Silencing cell phone");
    }

    /**
     * 就坐
     */
    @Before("performance()")
    public void takeSeats(){
        System.out.println("Taking seats");
    }

    /**
     * @AFter 执行之后通知
     */
    @After("performance()")
    public void applause(){
        System.out.println("CLAP CLAP CLAP!!!");
    }

    /**
     * @AfterThrowing 执行异常后通知
     */
    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("Demanding a refund!!");
    }


}
