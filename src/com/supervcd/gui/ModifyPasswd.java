package com.supervcd.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.supervcd.db.action.UserData;
import com.supervcd.frame.Framework;

public class ModifyPasswd extends JDialog implements Framework {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//label标签
	private JLabel lbUser, lbPasswd, lbRepasswd;
	private JTextField tfUser, tfPasswd, tfRepasswd;
	//按钮
	private JButton btnModify,btnDeny;
	//账户，旧密码，新密码
	private String username;
	private String password;
	private String rpassword;
	
	UserData userData = UserData.getInstance();
	
	public ModifyPasswd(JFrame jf) {
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
		this.setTitle("SuperVCD-2");
		this.setBounds(520, 280, 350, 280);
		this.setResizable(false);
	}

	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
		lbUser = new JLabel("用户名");
		lbPasswd = new JLabel("旧密码");
		lbRepasswd = new JLabel("新密码");
		
		tfUser = new JTextField(userData.host);
		tfPasswd = new JTextField();
		tfRepasswd = new JTextField();
		
		btnModify = new JButton("修改");
		btnDeny = new JButton("取消");
		
		tfUser.setEditable(false);
	}

	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		
		btnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				username = tfUser.getText();
				password = tfPasswd.getText();
				rpassword = tfRepasswd.getText();
				if(username.equals("") && password.equals("") && rpassword.equals("")) {
					
					JOptionPane.showMessageDialog(null, "信息不能为空");
				}else {
					if(userData.modify(userData.host,password,rpassword)) {
						JOptionPane.showMessageDialog(null, "修改成功");
						toExit();
					}else {
						JOptionPane.showMessageDialog(null, "旧密码不正确");
					}
				}
				
				
			}
		});
		
		btnDeny.addActionListener(new ActionListener() {
			
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
		lbPasswd.setBounds(40, 70, 80, 35);
		lbRepasswd.setBounds(40, 110, 80, 35);
		
		tfUser.setBounds(120, 30, 180, 35);
		tfPasswd.setBounds(120, 70, 180, 35);
		tfRepasswd.setBounds(120, 110, 180, 35);
		
		btnModify.setBounds(50, 180, 70, 30);
		btnDeny.setBounds(220, 180, 70, 30);
	}

	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
		
		this.setLayout(null);
		this.add(lbUser);
		this.add(lbPasswd);
		this.add(lbRepasswd);
		
		this.add(tfUser);
		this.add(tfPasswd);
		this.add(tfRepasswd);
		
		this.add(btnModify);
		this.add(btnDeny);
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		this.dispose();
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new ModifyPasswd();
//	}

}
