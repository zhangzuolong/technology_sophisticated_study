package com.zhangzl.study.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Description  :
 * Create  User : zhangzuolong
 * Create  Time : 2017/7/31 14:57
 * //如果一个类带了@Service注解，将自动注册到Spring容器，不需要再在applicationContext.xml文件定义bean了，
 * // 类似的还包括@Component、@Repository、@Controller。
 */
@Service("performance")
public class MyPerformance implements Performance{
    public void perform() {
        System.out.println("... ... ... ... ... ");
    }
}
