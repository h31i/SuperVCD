package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supervcd.db.MyDatabaseFactory;

/**
 * @Author: PQ
 * @Data: 2019年1月4日下午10:06:03
 */
public class DatabaseUserTest {
	Connection conn = null;
	Statement stmt = null;

	// 创建与数据库的连接对象
	@Before
	public void beginDatabase() {
		conn = MyDatabaseFactory.getConnect();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试数据库连接
	@Test
	public void testDatabaseConnection() {
		if (conn != null) {
			System.out.println("连接成功");
		} else {
			System.out.println("连接失败");
		}
	}

	// 测试创建表
	@Test
	public void testDatabaseCreateTables() {
		String sql = "CREATE table IF NOT EXISTS users" + "(id INTEGER primary key autoincrement,"
				+ "username CHAR(20) NOT NULL UNIQUE," + "password CHAR(20) NOT NULL);";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试删除操作
	@Test
	public void testDatabaseInsertData() {
		String username = "admin";
		String password = "123456";
		String sql = "INSERT INTO users(username,password) VALUES('" 
		+ username 
		+ "','" 
		+ password 
		+ "');";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 测试查询操作
	@Test
	public void testDatabaseQueryData() {
		String sql = "SELECT * FROM users;";
//		String sql = "SELECT password from users WHERE username='admin';";//返回一个值
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试更新数据
	@Test
	public void testDatabaseUpdateData() {
		String sql = "UPDATE users set password = 'jiandan' where username = 'username';";

		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试删除操作哦
	@Test
	public void testDatabaseDeleteData() {
		String sql = "DELETE from users where username='admin'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭事务和数据库连接
	@After
	public void toClose() {
		testDatabaseQueryData();
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
