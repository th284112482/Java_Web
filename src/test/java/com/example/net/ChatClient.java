package com.example.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient extends JFrame {
    private JTextArea ta;
    private JTextField tf;
    private PrintWriter pw;

    public ChatClient() {
        setTitle("聊天软件 - 客户端");
        setSize(400, 300);
        setDefaultCloseOperation(3);
        ta = new JTextArea();
        ta.setEditable(false);
        tf = new JTextField();

        JButton btn = new JButton("发送");
        btn.addActionListener(this::sendMessage);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tf, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.EAST);

        add(new JScrollPane(ta), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        connectServer();
        setVisible(true);
    }

    private void connectServer() {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            pw = new PrintWriter(socket.getOutputStream(), true);
            ta.append("已连接服务端！\n");
        } catch (Exception e) {
            ta.append("连接失败！\n");
        }
    }

    private void sendMessage(ActionEvent e) {
        String msg = tf.getText().trim();
        if (!msg.isEmpty()) {
            pw.println(msg);
            ta.append("我：" + msg + "\n");
            tf.setText("");
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}