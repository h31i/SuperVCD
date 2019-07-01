package com.supervcd.data.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UserData {
	//用户数据库
	Map<String,String> table = new LinkedHashMap<String,String>();
	//用一个数组代表用户的在线状态
	TreeMap<String,String> powerTable = new TreeMap<String,String>();
	
	//读写文件
	BufferedReader br = null;
	PrintWriter pw = null;
	
	private String user;
	private String pwd;
	
	//使用单例模式
	static UserData instance = new UserData();
	private UserData() {
		getData();
	}
	
	public static UserData getInstance() {
		return instance;
	}
	
	//将数据读出
	public void getData() {
		String[] upo = new String[3];
		
		try {
			br = new BufferedReader(new FileReader(".\\db\\user.db"));
			String line;
			while((line = br.readLine()) != null){
				upo= line.split(",");
				table.put(upo[0], upo[1]);
				powerTable.put(upo[0], upo[2]);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//登陆检查
	public Boolean login(String user,String pwd) {
		if(table.containsKey(user) && table.get(user).equals(pwd) ){
			return true;
		}else{
			return false;
		}
	}
	
	//添加用户
	public Boolean addUser(String user, String pwd, String repwd) {
		if(powerTable.containsKey(user)) {
			return false;
		}else {
			table.put(user, pwd);
			powerTable.put(user, "0");
			save();
			return true;
		}
	}
	
	//输出当前表数据
	public void show() {
		System.out.println(table);
		System.out.println(powerTable);
	}
	
	//修改密码
	public Boolean modify(String user,String passwd,String repasswd) {
		//判断密码是否正确
		if(!table.get(user).equals(passwd))
			return false;
		//修改密码
		table.replace(user, passwd, repasswd);
		save();
		return true;
	}
	
	//更新文件数据
	public void save() {
		Set<String> keySet = table.keySet();
		Iterator<String> it = keySet.iterator();
		try {
			pw = new PrintWriter(new FileWriter(".\\db\\user.db"));
			while(it.hasNext()) {
				this.user = (String) it.next();
				this.pwd = table.get(user);
				
				String output = this.user+","+this.pwd+","+powerTable.get(this.user);
				System.out.println("");
				pw.println(output);
				pw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
