package com.zhangzl.study.broker.server;

import com.zhangzl.study.broker.message.CallMessage;

/**
 * Description  : 服务端代理
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 14:58
 */
public class ServerProxy {

    String methodName;
    String params;
    int paramIntOne;
    int paramIntTwo;
    String strToCount;
    CallMessage resultMsgServer;
    int result;

    public ServerProxy(CallMessage msgServer){

        Server s = new Server();

        methodName = msgServer.getMethodName();
        params = msgServer.getParams();

        if(methodName.equals("addIntegers")){
            parseInts();
            System.out.println("(执行加操作)Server About To Perform Add Operation...");
            result = s.addIntegers(paramIntOne,paramIntTwo);
        }else if(methodName.equals("getLength")){
            parseString();
            System.out.println("(执行求长度操作)Server About to Perform GetLength...");
            result = s.getLength(strToCount);
        }
    }

    /**
     * 格式化参数
     */
    private void parseString(){
        int index = params.indexOf("|");
        strToCount = params.substring(index+1,params.length());
    }

    private void parseInts(){
        int index = params.indexOf("|");
        paramIntOne = Integer.parseInt(params.substring(0, index));
        paramIntTwo = Integer.parseInt(params.substring(index+1, params.length()));
    }

    public CallMessage retrunResult(){
        System.out.println("(返回结果)Demarshalling Data...");
        resultMsgServer = new CallMessage(result);
        return resultMsgServer;
    }
}
