package com.supervcd.db.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import com.supervcd.db.MyDatabaseFactory;

public class UserData {
	
	Connection conn = null;
	public String host = null;
	//用户数据库
	Map<String,String> table = new LinkedHashMap<String,String>();
	
	//使用单例模式
	static UserData instance = new UserData();
	private UserData() {
		getData();
		show();
	}
	
	public static UserData getInstance() {
		return instance;
	}
	
	//将数据读出
	public void getData() {
		
		conn = MyDatabaseFactory.getConnect();
		
		String sql_query = "SELECT * from users;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql_query);
			while (rs.next()) {			
				table.put(rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//登陆检查
	public Boolean login(String user,String pwd) {
		
//		try {
//			Statement stmt = conn.createStatement();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		if(table.containsKey(user) && table.get(user).equals(pwd) ){
			this.host = user;
			return true;
		}else{
			return false;
		}
	}
	
	//添加用户
	public Boolean addUser(String user, String pwd, String repwd) {

		if(table.containsKey(user)) {
			return false;
		}else {
			table.put(user, pwd);
			add(user, pwd);
			return true;
		}
	}
	
	//输出当前表数据
	public void show() {
		System.out.println(table);
	}
	
	//修改密码
	public Boolean modify(String user,String passwd,String repasswd) {
		//判断密码是否正确
		if(!table.get(user).equals(passwd))
			return false;
		//修改密码
		table.replace(user, passwd, repasswd);
		modifyPassword(user,repasswd);
		return true;
	}
	
	//更新文件数据
	public void modifyPassword(String username,String password) {
		String sql = "UPDATE users set password = '"
				+ password
				+ "' where username = '"
				+ username
				+ "';";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(String username , String password) {
		String sql = "INSERT INTO users(username,password) VALUES('" 
				+ username 
				+ "','" 
				+ password 
				+ "');";
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
