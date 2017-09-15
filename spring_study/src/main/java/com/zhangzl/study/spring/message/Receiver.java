package com.zhangzl.study.spring.message;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Description  : 消息的接受者
 * Create  User : zhangzuolong
 * Create  Time : 2017/8/1 17:34
 */
public class Receiver {

    public static void main(String[] args) {
        //ConnectionFactory:连接工厂 JMS用它创建连接
        ConnectionFactory connectionFactory;
        //JMS 客户端到JMS Provider的连接
        Connection connection = null;
        //Session : 一个发送或接受信息
        Session session;
        //Destination :消息的目的地；消息发给谁
        Destination destination;
        //MessageConsumer 消费者 消息的接受者
        MessageConsumer consumer;
        //TextMessage message
        //构造ConnectionFactory实例对象，此处采用ActiveMQde 的实现jar
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616");
        try{
            //从构造工厂得到连接对象
            connection = connectionFactory.createConnection();
            //启动
            connection.start();
            //获取操作连接
            /**
             createSession(paramA,paramB);
             paramA是设置事务的，paramB设置acknowledgment mode
             paramA设置为false时：paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
             paramA设置为true时：paramB的值忽略， acknowledgment mode被jms服务器设置为SESSION_TRANSACTED 。
             Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。
             Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。
             DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
             在需要考虑资源使用时，这种模式非常有效
             */
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //获取Session  注意参数是一个服务器的queue（队列）
            destination = session.createQueue("FirstQueue");
            //得到消息商消费者[接受方]
            consumer = session.createConsumer(destination);
            while (true){
                //设置接受者接受消息的额时间，为了便于测试，这里设定为100s
                TextMessage message = (TextMessage) consumer.receive(500000);
                if(null != message){
                    System.out.println("收到消息："+message.getText());
                }else{
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(null!= connection){
                    connection.close();
                }
            }catch (Throwable ignore){
                System.out.println(ignore.getMessage());
            }

        }
    }
}
