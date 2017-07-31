package com.zhangzl.study.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description  :
 * Create  User : zhangzuolong
 * Create  Time : 2017/7/31 15:22
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("/spring/aop/performance.xml");
        Performance performance = appContext.getBean(Performance.class);
        performance.perform();
        ((ClassPathXmlApplicationContext)appContext).close();
    }

}
