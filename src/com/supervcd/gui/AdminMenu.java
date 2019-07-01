package com.supervcd.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
* @Author: PQ  
* @Data: 2018年8月24日上午2:10:18 
*/
public class AdminMenu extends JMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem userMage;
	private JMenuItem vcdAdd;
	
	
	public AdminMenu() {
		this.setText("数据管理");
		initMenu();
		addMenu();
		addVcd();
	}
	
	private void initMenu() {
		userMage = new JMenuItem("用户管理");
		vcdAdd = new JMenuItem("添加VCD");
	}
	private void addMenu() {
		this.add(userMage);
		this.addSeparator();
		this.add(vcdAdd);
	}
	private void addVcd() {
		vcdAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AddMusicVcd();
			}
		});
	}
}
