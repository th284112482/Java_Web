package com.example.net;

import java.net.InetAddress;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            // 1. 获取本机IP和主机名
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("本机主机名：" + localHost.getHostName());
            System.out.println("本机IP地址：" + localHost.getHostAddress());

            // 2. 根据域名获取服务器IP
            InetAddress baidu = InetAddress.getByName("www.baidu.com");
            System.out.println("百度主机名：" + baidu.getHostName());
            System.out.println("百度IP地址：" + baidu.getHostAddress());

            // 3. 判断是否可达（测试网络连通）
            System.out.println("是否可达：" + baidu.isReachable(3000)); // 3秒超时

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}