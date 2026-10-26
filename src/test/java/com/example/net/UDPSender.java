package com.example.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// UDP发送端
public class UDPSender {
    public static void main(String[] args) {
        try {
            // 1. 创建UDP Socket对象（随机端口）
            DatagramSocket socket = new DatagramSocket();

            // 2. 准备要发送的数据
            String msg = "Hello UDP! 我是发送端~";
            byte[] data = msg.getBytes();

            // 3. 封装数据包（数据 + 长度 + 目标IP + 目标端口）
            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    InetAddress.getLocalHost(), // 目标IP
                    8888                        // 目标端口
            );

            // 4. 发送数据
            socket.send(packet);
            System.out.println("UDP消息发送成功！");

            // 5. 关闭资源
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}