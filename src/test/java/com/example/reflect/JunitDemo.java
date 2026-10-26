package com.example.reflect;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JunitDemo {

    public int add(int a, int b) {
        return a + b;
    }

    @Test
    public void testAdd() {
        int result = add(2, 3);
        assertEquals(5, result);
        System.out.println("测试通过");
    }
}
