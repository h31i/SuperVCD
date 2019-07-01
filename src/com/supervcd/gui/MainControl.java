package com.supervcd.gui;

import com.supervcd.db.action.AlbumDataPool;

public class MainControl {
	AlbumDataPool adp = AlbumDataPool.getPool();
	public MainControl() {
		adp.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainControl();
	}

}
