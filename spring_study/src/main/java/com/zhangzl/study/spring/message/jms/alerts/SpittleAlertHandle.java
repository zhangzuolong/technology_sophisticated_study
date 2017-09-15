package com.zhangzl.study.spring.message.jms.alerts;

import com.zhangzl.study.spring.message.jms.domain.Spittle;

/**
 * Description  :
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/2 10:38
 */
public class SpittleAlertHandle {

    public void handleSpittleAlert(Spittle spittle) {
        System.out.println(spittle.getMessage());
    }
}
