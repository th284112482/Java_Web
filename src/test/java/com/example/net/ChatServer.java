package com.example.net;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends JFrame {
    private JTextArea ta;
    private ServerSocket serverSocket;

    public ChatServer() {
        setTitle("聊天软件 - 服务端");
        setSize(400, 300);
        setDefaultCloseOperation(3);
        ta = new JTextArea();
        ta.setEditable(false);
        add(new JScrollPane(ta));
        setVisible(true);
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(8888);
            ta.append("服务端已启动，等待客户端连接...\n");
            Socket socket = serverSocket.accept();
            ta.append("客户端已连接！\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = br.readLine()) != null) {
                ta.append("客户端：" + msg + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer().startServer();
    }
}