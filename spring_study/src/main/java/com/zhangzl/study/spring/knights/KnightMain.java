package com.zhangzl.study.spring.knights;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description  :
 * Class   Path : com.zhangzl.study.spring.knights
 * Create  User : zhangzuolong
 * Create  Time : 2017/6/27 11:16
 * Project Name : technology_sophisticated_study
 */
public class KnightMain {

    public static void main(String[] args) {
        /**
         * application context 应用上下文
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/knights/knights.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}
