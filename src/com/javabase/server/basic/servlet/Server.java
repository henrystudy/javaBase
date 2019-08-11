package com.javabase.server.basic.servlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @description: 写一个服务端等待浏览器客户端访问并获取客户请求信息
 * @author: Henry Zheng
 * @date: Created in 16:00 2019/8/4
 * @modified by:
 */

public class Server {
    private ServerSocket serverSocket;

    public static void main(String[] args){
        new Server().start();
    }

    //启动服务
    public void start() {
        System.out.println("----服务器启动----");
        //1. 指定端口，使用ServerSocket创建服务器
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动异常");
        }

        receive();
    };

    //接收连接处理
    public void receive(){
        //2. 阻塞式等待连接accept，监听，返回一个Socket
        Socket client = null;
        try {
            client = serverSocket.accept();
            System.out.println("一个客户端建立了连接");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
        //3. 操作: 输入输出流操作(这里简单点一次读取进来，正常服务器应该时逐行或者逐个字节读取)
        DataInputStream in = null;
        try {
            in = new DataInputStream(client.getInputStream());
            byte[] datas = new byte[1024*1024];
            int lens = in.read(datas);
            String requestData = new String(datas,0,lens);
            System.out.println(requestData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取客户端信息异常");
        }

        //返回响应, 必须严格遵守http协议
        StringBuilder contents = new StringBuilder();
        contents.append("<html>");
        contents.append("<header>");
        contents.append("<title>");
        contents.append("server响应");
        contents.append("</title>");
        contents.append("/header>");
        contents.append("<body>");
        contents.append("。。。响应终于回来了。。。");
        contents.append("</body>");
        contents.append("</html>");

        //注意响应长度时字节长度，不是字符长度contents.length()
        int size = contents.toString().getBytes().length;
        StringBuilder responseData = new StringBuilder();
        //处理空格和换行
        String blank = " ";
        String CRLF = "\r\n";

        //1. 响应行
        responseData.append("HTTP/1.1").append(blank);
        responseData.append(200).append(blank);
        responseData.append("OK").append(CRLF);

        //2. 响应头（最后一行存在空行）
        responseData.append("Date: ").append(new Date()).append(CRLF);
        responseData.append("Server: ").append("测试服务器/0.0.1;charset=GBK").append(CRLF);
        responseData.append("Content-Type: ").append("text/html;").append(blank).append("charset=UTF-8").append(CRLF);
        responseData.append("Content-Length: ").append(size).append(CRLF);
        responseData.append(CRLF);

        //3. 正文
        responseData.append(contents);

        //输出到客户端
        OutputStream out = null;
        try {
            out = client.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
            bw.write(responseData.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    //停止服务
    public void stop(){
        //4. 释放资源
    };
}
