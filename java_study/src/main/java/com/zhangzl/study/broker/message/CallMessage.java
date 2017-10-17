package com.zhangzl.study.broker.message;

/**
 * Description  : 消息内容
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 11:01
 */
public class CallMessage {
    private String methodName;
    private String params;
    private Integer result;

    public CallMessage(String methodName, String params) {
        this.methodName = methodName;
        this.params = params;
    }

    public CallMessage(Integer result) {
        this.result = result;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
