package com.zhangzl.study.broker.client;

/**
 * Description  : 客户端
 * Create  User : zhangzl
 * Create  Time : 2017/10/16 14:52
 */
public class Client {

    public static void main(String[] args) {
        ClientProxy cp = new ClientProxy();

        System.out.println("Calling AddIntegers on 10 and 20...");
        cp.addIntegers(10,20);

        System.out.println("-----------------------------------");
        System.out.println("Calling GetLength on 'JAVA'");
        cp.getLength("Java");
    }
}
