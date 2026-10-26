package com.example.reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// 1. 自定义注解
@Retention(RetentionPolicy.RUNTIME)
@interface MyTest {
    String value() default "默认值";
}

// 2. 使用注解
class TestClass {
    @MyTest("注解测试")
    public void test() {
        System.out.println("执行test方法");
    }
}

// 3. 解析注解
public class AnnotationDemo {
    public static void main(String[] args) throws Exception {
        Class<TestClass> clazz = TestClass.class;
        Method method = clazz.getMethod("test");
        
        // 获取注解
        MyTest anno = method.getAnnotation(MyTest.class);
        System.out.println("注解值：" + anno.value());
        
        // 执行方法
        method.invoke(clazz.newInstance());
    }
}