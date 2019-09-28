package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import vcd.db.action.MyDatabaseFactory;

/**
 * @Author: PQ
 * @Data: 2019年1月5日下午5:41:53
 */
public class DatabaseAlbumTest {
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

	@Test
	public void testQueryAlbums() {
		String sql = "select * from albums;";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {

				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭事务和数据库连接
	@After
	public void toClose() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
