package com.zhangzl.study.broker.server;

import com.zhangzl.study.broker.message.CallMessage;

/**
 * Description  : 服务经纪人
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 15:09
 */
public class BrokerServer {
    String methodName;
    String params;
    CallMessage msgServer;
    String message;

    public BrokerServer(String currMessage){
        //处理传入数据，分割查询方法和查询参数
        parseMessage(currMessage);

        System.out.println("Server Broker Transferring Data to Server Proxy...");
        ServerProxy sp = new ServerProxy(msgServer);

        System.out.println("Returning Results...");
        msgServer = sp.retrunResult();
        setMessage(msgServer.getResult().toString());
    }

    private void parseMessage(String message){
        int index = message.indexOf("|");
        methodName = message.substring(0,index);
        params = message.substring(index+1,message.length());

        msgServer = new CallMessage(methodName,params);
    }

    public CallMessage getMsgServer() {
        return msgServer;
    }

    public void setMsgServer(CallMessage msgServer) {
        this.msgServer = msgServer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
