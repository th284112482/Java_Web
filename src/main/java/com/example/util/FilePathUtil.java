package com.example.util;

import java.io.File;

public class FilePathUtil {
    // 定义数据存储根目录（可自行修改，确保有读写权限）
    private static final String BASE_PATH = "D:/crud_data/";

    static {
        File dir = new File(BASE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String getBasePath() {
        return BASE_PATH;
    }

    // 获取CSV导出文件路径（临时）
    public static String getCsvExportPath() {
        return BASE_PATH + "users_export.csv";
    }

    // 获取对象序列化备份文件路径
    public static String getSerBackupPath() {
        return BASE_PATH + "users_backup.ser";
    }

    // 获取数据统计文件路径
    public static String getStatsFilePath() {
        return BASE_PATH + "stats.dat";
    }
}