package com.zhangzl.study.broker.client;

import com.zhangzl.study.broker.message.CallMessage;

/**
 * Description  : 客户端代理
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 11:08
 */
public class ClientProxy {
    String currMethodName;
    String currParam;

    public void addIntegers(Integer a,Integer b){
        currMethodName="addIntegers";
        currParam=(a+"|"+b);
        prepareData();
    }

    public void getLength(String str){
        currMethodName="getLength";
        currParam = str;
        prepareData();
    }

    private void prepareData(){
        System.out.println("Client Proxy Marshalling Data...");
        CallMessage msgClient = new CallMessage(currMethodName,currParam);

        System.out.println("Client Proxy Forwarding Data...");
        BrokerClient bc = new BrokerClient(msgClient);

        System.out.println("Client Proxy Demarshalling For Final Result");
        displayResult(bc);

    }

    private void displayResult(BrokerClient bc){
        int result = bc.getResultMessage().getResult();
        System.out.println("Your result is:"+result);
    }


}
