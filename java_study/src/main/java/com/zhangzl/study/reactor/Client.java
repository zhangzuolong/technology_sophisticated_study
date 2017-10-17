package com.zhangzl.study.reactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Description  : 客户端
 * Create  User : zhangzl
 * Create  Time : 2017/10/17 10:12
 */
public class Client {
    String hostIp;
    int hostPort;

    public Client(String hostIp,int hostPort){
        this.hostIp = hostIp;
        this.hostPort = hostPort;
    }

    public void runClient() throws IOException{
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try{
            clientSocket = new Socket(hostIp,hostPort);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch (UnknownHostException e1){
            System.err.println("unKnown host:"+hostIp+" -->"+e1.getMessage());
            System.exit(1);
        }catch (IOException e2){
            System.err.println("Couldn't connect to:"+hostIp +"-->"+e2.getMessage());
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Client connected to host : "+hostIp+" port: "+hostPort);
        System.out.println("Tell what your name is to the Server......");

        while ((userInput = stdIn.readLine())!=null){
            out.println(userInput);
            System.out.println("Server says:"+in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",9900);
        try {
            client.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
