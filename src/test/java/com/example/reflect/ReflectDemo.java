package com.example.reflect;

import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        // 1. 获取Class对象（必须写全类名：包名+类名）
        Class<?> clazz = Class.forName("com.example.reflect.Student");

        // 2. 创建对象
        Object obj = clazz.newInstance();

        // 3. 获取方法
        Method method = clazz.getDeclaredMethod("show");

        // 4. 执行方法
        method.invoke(obj);
    }
}