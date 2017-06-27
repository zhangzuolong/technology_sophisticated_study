package com.zhangzl.study.spring.knights.config;

import com.zhangzl.study.spring.knights.BraveKnight;
import com.zhangzl.study.spring.knights.Knight;
import com.zhangzl.study.spring.knights.Quest;
import com.zhangzl.study.spring.knights.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description  : java 装配
 * Class   Path : com.zhangzl.study.spring.knights.config
 * Create  User : zhangzuolong
 * Create  Time : 2017/6/27 11:27
 * Project Name : technology_sophisticated_study
 *
 * 用@Configuration注解该类，等价 与XML中配置beans
 * 用@Bean标注方法等价于XML中配置bean
 *
 */
@Configuration
public class KnightConfig {

    @Bean
    public Knight knight(){
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest(){
        return new SlayDragonQuest(System.out);
    }
}
