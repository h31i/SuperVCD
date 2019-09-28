package vcd.db.action;

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

	/**
	 * 用的工厂模式，代码逻辑应该更加清晰才对，但是并没有
	 * 感觉一年前设计的不太合理，有待改进
	 */

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
