package com.example.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 线程不安全：多线程并发扣钱，金额会错乱
public class UnsafeAccountDemo {

    public static void main(String[] args) {
        // 启动10个线程，每个线程都执行扣钱操作
        for (int i = 0; i < 10; i++) {
            // 传统写法：匿名内部类创建线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reduceMoney();
                }
            }).start();
        }
    }

    // 不安全的扣钱方法
    public static void reduceMoney() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();

            // 1. 查询当前金额
            String selectSql = "select money from account where id=1";
            PreparedStatement ps = conn.prepareStatement(selectSql);
            ResultSet rs = ps.executeQuery();
            int money = 0;
            if (rs.next()) {
                money = rs.getInt("money");
            }

            // 模拟业务耗时，放大并发问题
            Thread.sleep(50);

            // 2. 扣钱 10 元
            money = money - 10;

            // 3. 更新回数据库
            String updateSql = "update account set money=? where id=1";
            PreparedStatement ps2 = conn.prepareStatement(updateSql);
            ps2.setInt(1, money);
            ps2.executeUpdate();

            System.out.println("扣钱成功，剩余：" + money);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {}
        }
    }
}