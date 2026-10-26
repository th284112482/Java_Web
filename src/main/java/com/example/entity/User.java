package com.example.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private int age;

    // 无参构造
    public User() {}

    // 全参构造
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter 和 Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}