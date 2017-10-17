package com.zhangzl.study.broker.client;

import com.zhangzl.study.broker.message.CallMessage;
import com.zhangzl.study.broker.transport.Transport;

/**
 * Description  : 中间代理
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 14:35
 */
public class BrokerClient {

    String methodName;
    String params;
    String message;
    CallMessage resultMessage;

    public BrokerClient(CallMessage msg){
        methodName = msg.getMethodName();
        params = msg.getParams();

        //convert message to String
        System.out.println("Client Broker Converting to String...");
        message = (methodName+"|"+params);

        System.out.println("Client Broker forwarding Bytes to Transfer...");
        Transport t = new Transport(message);


        this.setResultMessage(new CallMessage(Integer.parseInt(t.getMessage())));
    }

    public CallMessage getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(CallMessage resultMessage) {
        this.resultMessage = resultMessage;
    }
}
