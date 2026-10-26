package com.example.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 1. 接口
interface UserService {
    void save();
}

// 2. 实现类
class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("保存用户");
    }
}

// 3. 代理处理器
class MyHandler implements InvocationHandler {
    private Object target;
    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开启事务");
        Object result = method.invoke(target, args);
        System.out.println("提交事务");
        return result;
    }
}

// 4. 测试
public class ProxyDemo {
    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        UserService proxy = (UserService) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new MyHandler(target)
        );
        proxy.save();
    }
}