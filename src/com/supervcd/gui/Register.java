package com.supervcd.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.supervcd.db.action.UserData;
import com.supervcd.frame.Framework;

public class Register extends JDialog implements Framework {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbUser, lbPwd, lbRepwd;
	private JTextField addUser;
	private JPasswordField addPwd,addRepwd;
	private JButton btReg,btCancel;
	
	//创建与用户信息的接口
	UserData ud = UserData.getInstance();
	
	public Register(JFrame jf) {
		super(jf,true);
		frameMain();
		frameInitial();
		frameAction();
		frameLocate();
		frameAdd();
		
		this.setVisible(true);
	}
	@Override
	public void frameMain() {
		// TODO Auto-generated method stub
		this.setTitle("注册用户");
		this.setBounds(400, 400, 330, 275);
		this.setResizable(false);
	}

	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
		
		lbUser = new JLabel("账户名");
		lbPwd = new JLabel("密码");
		lbRepwd = new JLabel("确认密码");
		
		addUser = new JTextField();
		addPwd = new JPasswordField();
		addRepwd = new JPasswordField();
		
		btReg = new JButton("确定");
		btCancel = new JButton("取消");
	}

	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		
		btReg.addActionListener(new ActionListener() {
			String adduser,addpwd,addrepwd;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adduser = addUser.getText();
				addpwd = String.valueOf(addPwd.getPassword());
				addrepwd = String.valueOf(addRepwd.getPassword());
				
				if(addpwd.length()<6) {
					JOptionPane.showMessageDialog(null,"密码小于6位");
				}else if(addpwd.equals(addrepwd)){
					if(ud.addUser(adduser, addpwd, addrepwd)) {
						JOptionPane.showMessageDialog(null,"注册成功");
						toExit();
					}else {
						JOptionPane.showMessageDialog(null,"用户名已被注册");
					}
				}else{
					JOptionPane.showMessageDialog(null,"两次密码不一致");
				}
			}
		});
		
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				toExit();
			}
		});
	}

	@Override
	public void frameLocate() {
		// TODO Auto-generated method stub
		lbUser.setBounds(40, 30, 80, 35);
		lbPwd.setBounds(40, 70, 80, 35);
		lbRepwd.setBounds(40, 110, 80, 35);
		
		addUser.setBounds(120, 30, 180, 35);
		addPwd.setBounds(120, 70, 180, 35);
		addRepwd.setBounds(120, 110, 180, 35);
		
		btReg.setBounds(50, 180, 70, 30);
		btCancel.setBounds(220, 180, 70, 30);
	}

	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		this.add(lbUser);
		this.add(lbPwd);
		this.add(lbRepwd);
		this.add(addUser);
		this.add(addPwd);
		this.add(addRepwd);
		this.add(btReg);
		this.add(btCancel);
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
