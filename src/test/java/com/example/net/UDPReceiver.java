package com.example.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

// UDP接收端
public class UDPReceiver {
    public static void main(String[] args) {
        try {
            // 1. 创建Socket，绑定端口8888
            DatagramSocket socket = new DatagramSocket(8888);

            // 2. 创建字节数组接收数据
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("UDP接收端已启动，等待消息...");

            // 3. 阻塞接收数据
            socket.receive(packet);

            // 4. 解析接收的数据
            String msg = new String(packet.getData(), 0, packet.getLength());
            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();

            System.out.println("收到来自[" + ip + ":" + port + "]的消息：" + msg);

            // 5. 关闭
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}