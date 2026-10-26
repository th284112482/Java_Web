package com.example.service;

import com.example.util.FilePathUtil;
import java.io.*;

public class StatsService {
    private static final String STATS_FILE = FilePathUtil.getStatsFilePath();

    // 读取统计信息，返回长度为4的int数组 [addCount, deleteCount, updateCount, queryCount]
    public static int[] readStats() {
        int[] stats = new int[4]; // 0:add, 1:delete, 2:update, 3:query
        try (DataInputStream dis = new DataInputStream(new FileInputStream(STATS_FILE))) {
            stats[0] = dis.readInt();
            stats[1] = dis.readInt();
            stats[2] = dis.readInt();
            stats[3] = dis.readInt();
        } catch (FileNotFoundException e) {
            // 文件不存在，返回全0
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stats;
    }

    // 增加某种操作的计数
    public static synchronized void increment(String operation) {
        int[] stats = readStats();
        switch (operation) {
            case "add":    stats[0]++; break;
            case "delete": stats[1]++; break;
            case "update": stats[2]++; break;
            case "query":  stats[3]++; break;
            default: return;
        }
        saveStats(stats);
    }

    private static void saveStats(int[] stats) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(STATS_FILE))) {
            dos.writeInt(stats[0]);
            dos.writeInt(stats[1]);
            dos.writeInt(stats[2]);
            dos.writeInt(stats[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}