package com.example.net;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// TCP服务端
public class TCPServer {
    public static void main(String[] args) {
        try {
            // 1. 启动服务端，绑定端口
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("TCP服务端已启动，等待客户端连接...");

            // 2. 阻塞等待客户端连接（三次握手底层自动完成）
            Socket socket = serverSocket.accept();
            System.out.println("客户端已连接：" + socket.getInetAddress().getHostAddress());

            // 3. 获取输入流，读取客户端发送的数据
            InputStream is = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len = is.read(buffer);
            String msg = new String(buffer, 0, len);

            System.out.println("收到客户端消息：" + msg);

            // 4. 关闭资源
            is.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}