package com.javabase.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建服务器
 * 1. 建立连接，使用Socket创建客户端+ 服务器的地址和端口
 * 2. 操作: 输入输出流操作
 * 3. 释放资源
 * @author Henry
 * @date 07/21/2019
 */

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("----客户端----");
        //1. 建立连接，使用Socket创建客户端+ 服务器的地址和端口
        // 创建流套接字并将其连接到指定IP地址的指定端口号
        Socket client = new Socket("localhost",8888);
        //2. 操作: 输入输出流操作
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        String data = "Client: hello";
        out.writeUTF(data);
        out.flush();
        //3. 释放资源
        out.close();
        client.close();
    }
}
