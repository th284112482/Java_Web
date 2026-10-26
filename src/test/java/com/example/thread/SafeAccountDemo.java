package com.example.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 线程安全：保证金额一致
public class SafeAccountDemo {

    // 锁对象
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            // 传统写法，完全看懂版
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reduceMoneySafe();
                }
            }).start();
        }
    }

    public static void reduceMoneySafe() {
        // 同步锁：保证同一时间只有一个线程执行
        synchronized (lock) {
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
                conn.setAutoCommit(false); // 开启事务

                // for update 加行锁，防止其他事务同时修改
                String selectSql = "select money from account where id=1 for update";
                PreparedStatement ps = conn.prepareStatement(selectSql);
                ResultSet rs = ps.executeQuery();
                int money = 0;
                if (rs.next()) {
                    money = rs.getInt("money");
                }

                Thread.sleep(50);

                money = money - 10;

                String updateSql = "update account set money=? where id=1";
                PreparedStatement ps2 = conn.prepareStatement(updateSql);
                ps2.setInt(1, money);
                ps2.executeUpdate();

                conn.commit();
                System.out.println("扣钱成功，剩余：" + money);

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (Exception ex) {}
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {}
            }
        }
    }
}