package com.zhangzl.study.spring.message.jms.alerts;

import com.zhangzl.study.spring.message.jms.domain.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Description  :
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/2 10:12
 */
public class AlertServiceImpl implements AlertService{

    /**
     * 注入JMS模板
     */
    @Autowired
    private JmsOperations jmsOperations;

    @Override
    public void sendSpittleAlert(Spittle spittle) {
        jmsOperations.send("spittle.alert.queue",                //指定目的地
                new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(spittle);        //创建消息
            }
        });

    }
}
