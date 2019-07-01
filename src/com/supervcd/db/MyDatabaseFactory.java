package com.supervcd.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
* @Author: PQ  
* @Data: 2019年1月4日下午9:06:12 
* @
*/
public class MyDatabaseFactory {
	private static final String URL = "jdbc:sqlite:.\\db\\supervcd.db";
	
	static Connection conn = null;
	
	public MyDatabaseFactory() {
	}
	
	public static Connection getConnect(){
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接失败");
			System.exit(0);
		}		
		return conn;
	}

}
