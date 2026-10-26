package com.example.net;

import java.io.OutputStream;
import java.net.Socket;

// TCP客户端
public class TCPClient {
    public static void main(String[] args) {
        try {
            // 1. 连接服务端（IP + 端口）
            Socket socket = new Socket("127.0.0.1", 9999);
            System.out.println("成功连接到TCP服务端！");

            // 2. 获取输出流，发送数据
            OutputStream os = socket.getOutputStream();
            os.write("Hello TCP! 我是客户端~".getBytes());

            System.out.println("消息发送完成！");

            // 3. 关闭
            os.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}