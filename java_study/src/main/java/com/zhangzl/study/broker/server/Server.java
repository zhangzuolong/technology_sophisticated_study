package com.zhangzl.study.broker.server;

import com.zhangzl.study.broker.service.OperationsInterface;

/**
 * Description  : 具体的服务方法
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 14:46
 */
public class Server implements OperationsInterface{

    @Override
    public int addIntegers(int val1, int val2) {
        return (val1+val2);
    }

    @Override
    public int getLength(String str) {
        return str.length();
    }
}
