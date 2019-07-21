package com.javabase.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建服务器
 * 1. 指定端口，使用ServerSocket创建服务器
 * 2. 阻塞式等待连接accept
 * 3. 操作: 输入输出流操作
 * 4. 释放资源
 * @author Henry
 * @date 20190721
 */

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("----服务器端----");
        //1. 指定端口，使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(8888);
        //2. 阻塞式等待连接accept，监听，返回一个Socket
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //3. 操作: 输入输出流操作
        DataInputStream in = new DataInputStream(client.getInputStream());
        String data = in.readUTF();
        System.out.println(data);
        //4. 释放资源
        in.close();
        client.close();

        //一般Server都是7x24开机，如果需要维护也可以关机
        server.close();
    }
}
