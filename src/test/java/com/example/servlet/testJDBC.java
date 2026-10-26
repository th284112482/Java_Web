package com.example.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.example.entity.User;
import com.example.util.DBUtil;

public class testJDBC {

	@Test
	public  void testList () throws SQLException{

		List<User> list = new ArrayList<>();

		String sql = "SELECT * FROM user ORDER BY id";
		
		
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		 while (rs.next()) {
             User user = new User();
             user.setId(rs.getInt("id"));
             user.setName(rs.getString("name"));
             user.setAge(rs.getInt("age"));
             list.add(user);
         }
		 
		 for(User u:list) {
			 System.out.println(u.getName());
		 }

	}
	
	
	@Test
	public  void testMap () throws SQLException{

		Map<Integer,String> map = new HashMap<Integer,String>();

		String sql = "SELECT * FROM user ORDER BY id";
		
		
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		 while (rs.next()) {
             User user = new User();
        
             map.put(rs.getInt("id"), rs.getString("name"));

         }
		 
		 Set<Entry<Integer, String>> s = map.entrySet();
		 for(Entry en : s) {
			 System.out.println(map.get(en.getKey()));
		 }

	}
	
	@Test
	public void testSet() {
	    // 使用泛型，类型安全
	    Set<Integer> set = new TreeSet<>();
	    
	    set.add(2);
	    set.add(3);
	    set.add(1);
	    set.add(4);

	    // 方式1：增强for循环（最常用）
	    for (Integer num : set) {
	        System.out.println(num);
	    }

	    // 方式2：Stream / forEach（Java 8+）
//	    set.forEach(System.out::println);
	}
	
	   @Test
	    public void testLinkedList() {
	        // 创建链表
	        LinkedList<String> list = new LinkedList<>();

	        // 增
	        list.add("A");
	        list.add("B");
	        list.add("C");
	        list.addFirst("头部");
	        list.addLast("尾部");

	        // 删
	        list.remove(2);       // 删除下标2
	        list.remove("B");     // 删除元素B

	        // 查
	        System.out.println("第一个元素：" + list.getFirst());
	        System.out.println("最后一个元素：" + list.getLast());
	        System.out.println("下标1元素：" + list.get(1));

	        // 遍历
	        System.out.println("\n最终链表：");
	        for (String s : list) {
	            System.out.println(s);
	        }
	    }
	

}
