package com.example.reflect;



	import org.junit.jupiter.api.AfterEach;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	public class AnnotationDemo1 {

	    // 每个@Test执行前 自动执行
	    @BeforeEach
	    public void before() {
	        System.out.println("👉 @BeforeEach：初始化、打开连接、准备数据");
	    }

	    // 测试方法1
	    @Test
	    public void testMethod1() {
	        System.out.println("✅ 执行测试方法 1");
	    }

	    // 测试方法2
	    @Test
	    public void testMethod2() {
	        System.out.println("✅ 执行测试方法 2");
	    }

	    // 每个@Test执行后 自动执行
	    @AfterEach
	    public void after() {
	        System.out.println("👈 @AfterEach：关闭资源、释放连接、清理数据\n");
	    }
	}
