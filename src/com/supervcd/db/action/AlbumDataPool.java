package com.supervcd.db.action;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import com.supervcd.db.MyDatabaseFactory;

public class AlbumDataPool {
	
	Connection conn = null;
	//存放专辑信息
	private LinkedHashMap<String[], String[][]> table = new LinkedHashMap<String[],String[][]>();
	
	BufferedReader br = null;
	PrintWriter pw = null;
	
	//建立单例模式
	static AlbumDataPool instance = new AlbumDataPool();
	private AlbumDataPool() {
		createPool();
	}
	public static AlbumDataPool getPool() {
		return instance;
	}
	
	
	//向map中添加专辑信息
	public void poolAdd(String[] album,String[][] music) {
		table.put(album,music);
		
		String sql_album = "insert into albums(songer,album,address,image,number) "
				+ "values(?,?,?,?,?);";
		String sql_music = "insert into vcddetail(album,song,duration) values(?,?,?);";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql_album);
			pstmt.setString(1, album[0]);
			pstmt.setString(2, album[1]);
			pstmt.setString(3, album[2]);
			pstmt.setString(4, album[3]);
			pstmt.setString(5, album[4]);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql_music);
			
			for(int i = 0; i < music.length;i++) {
				pstmt.setString(1, album[1]);
				pstmt.setString(2, music[i][0]);
				pstmt.setString(3, music[i][1]);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//搜索目标
	public void toSearch(DefaultTableModel mdl, int n, String content) {
		reflushMdl(mdl);
		Set<String[]> keySet = table.keySet();
		Iterator<String[]> it = keySet.iterator();
		
		while(it.hasNext()) {
			String[] rowData = it.next();
			if(rowData[n].trim().equals(content)) {				
				mdl.addRow(rowData);
			}
		}
	}
	
	//将专辑信息添加到主页面
	public Boolean showTable(DefaultTableModel mdl) {
		reflushMdl(mdl);
		Set<String[]> keySet = table.keySet();
		Iterator<String[]> it = keySet.iterator();
			
		while(it.hasNext()) {
			String[] rowData = it.next();
			mdl.addRow(rowData);
		}	
		return true;
	}
	
	//刷新model，将model置空
	private void reflushMdl(DefaultTableModel model) {
		while(model.getRowCount() != 0) {
			model.removeRow(model.getRowCount()-1);
		}
	}
	
	//删除选中专辑数据
	public void remove(DefaultTableModel mdl, int i) {
		String[] targetData = {
				(String) mdl.getValueAt(i, 0),
				(String) mdl.getValueAt(i, 1),
				(String) mdl.getValueAt(i, 2),
				(String) mdl.getValueAt(i, 3),
				(String) mdl.getValueAt(i, 4),
		};
		
		Set<String[]> keySet = table.keySet();
		Iterator<String[]> it = keySet.iterator();
		
		while(it.hasNext()) {
			String[] test = it.next();
			if(compare(test,targetData)) {
				mdl.removeRow(i);
				table.remove(test);
				break;
			}
		}
		deleteAlbum(targetData[1]);
	}
	
	public void deleteAlbum(String album) {
		String sql_albums = "delete from albums where album=?;";
		String sql_details = "delete from vcddetail where album=?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql_albums);
			stmt.setString(1, album);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(sql_details);
			stmt.setString(1, album);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Boolean compare(String[] str1, String[] str2) {
			
		if(String.join(",", str1).equals(String.join(",", str2))) {
			return true;
		}else {
			return false;
		}	
	}
	
	//双击显示专辑歌曲信息
	public void showMusic(DefaultTableModel model,String[] testData) {
		reflushMdl(model); //刷新model
		
		Set<String[]> keySet = table.keySet();
		Iterator<String[]> it = keySet.iterator();

		while(it.hasNext()) {
			//读取表中数据
			String[] test = it.next();
			
			if( testData[0].equals(test[0]) && testData[1].equals(test[1])) {
				String[][] data = table.get(test);
				for(int i = 0 ;i < data.length;i++) {
					model.addRow(data[i]);
				}
				break;
			}
		}
	}
	
	//向控制台输出集合中的数据
	public void show() {
		Set<String[]> keySet = table.keySet();
		Iterator<String[]> it = keySet.iterator();
		
		while(it.hasNext()) {
			String[] data = it.next();
			System.out.println(Arrays.toString(data));
			System.out.println(Arrays.deepToString( (table.get(data)) ));
		}
		
	}
	
	//创建一个数据池，并加载到map中
	private void createPool() {
		conn = MyDatabaseFactory.getConnect();
		Statement stmt = null;
		String sql_albums = "select * from albums;";
	
		try {
			stmt = conn.createStatement();
			ResultSet rsAlbums = stmt.executeQuery(sql_albums);
		
			while(rsAlbums.next()) {
				String[] album = {rsAlbums.getString(2),
						rsAlbums.getString(3),
						rsAlbums.getString(4),
						rsAlbums.getString(5),
						rsAlbums.getString(6),
						};
				
				String sql_vcdDetails = "select * from vcddetail where album=?;";
				PreparedStatement pstmt = conn.prepareStatement(sql_vcdDetails);
				pstmt.setString(1, album[1]);
				
				ResultSet rsDetails = pstmt.executeQuery();
				
				String[][] details = new String[Integer.parseInt(album[4])][];

				for(int i = 0; rsDetails.next() ;i++) {
					String[] temp = {rsDetails.getString(2),rsDetails.getString(3)};
					details[i] = temp;
				}
				table.put(album, details);
			}
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	//保存将数据写入文件,
	public void savePool() {
		Set<String[]> albumSet = table.keySet();
		Iterator<String[]> itor = albumSet.iterator();
		
		try {
			pw = new PrintWriter(new FileWriter(".\\db\\music.db"));
			
			while(itor.hasNext()) {
				String[] albumKey = itor.next();
				String[][] musicValues = table.get(albumKey);
				//将数组连接成字符串,写入文件
				pw.println(String.join(",", albumKey));
				for(int i = 0 ; i < musicValues.length;i++) {
					pw.println(String.join(",", musicValues[i]));
				}
				pw.println("----------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pw.flush();
			pw.close();
		}
		
	}
}
