package com.zhangzl.study.broker.transport;


import com.zhangzl.study.broker.server.BrokerServer;

/**
 * Description  : 通过网络协议运输---这是测试就采用内存传输了
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 14:40
 */
public class Transport {

    private String message;

    public Transport(String currMessage){
        this.message = currMessage;

        System.out.println("(数据传递中)Transporting...");
        BrokerServer bs = new BrokerServer(message);

        System.out.println("(返回处理结果到客户端)Transporting Back to Client");
        this.setMessage(bs.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
