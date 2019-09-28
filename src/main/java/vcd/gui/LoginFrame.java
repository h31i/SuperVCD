package vcd.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import vcd.db.action.UserData;
import vcd.frame.Framework;

public class LoginFrame extends JFrame implements Framework {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbUser, lbPasswd, lbImage;
	private JTextField tfUser;
	private JPasswordField tfPasswd;
	private JButton btLogin,btReg;
	
	UserData ud = UserData.getInstance();
	
	public LoginFrame(){
		frameMain();
		frameInitial();
		frameAction();
		frameLocate();
		frameAdd();
		
		this.setVisible(true);
	}
	
	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
		
		lbUser = new JLabel("ÕË--ºÅ:");
		lbPasswd = new JLabel("ÃÜ--Âë:");
		lbImage = new JLabel();
		
		tfUser = new JTextField("");
		tfPasswd = new JPasswordField("");
		
		btLogin = new JButton("µÇÂ¼");
		btReg = new JButton("×¢²á");
	}

	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		
		btLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ud.login(tfUser.getText(), String.valueOf(tfPasswd.getPassword()))) {
					new HomePage();
					toExit();
				}else {
					JOptionPane.showMessageDialog(null, "ÓÃ»§»òÃÜÂë´íÎó");
					tfUser.setText("");
					tfPasswd.setText("");
				}
			}
		});
		
		btReg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				register();
			}
		});
	}
	
	@Override
	public void frameLocate() {
		// TODO Auto-generated method stub
		lbUser.setBounds(60, 90, 120, 35);
		tfUser.setBounds(115, 90, 155, 30);
		
		lbPasswd.setBounds(60, 135, 120, 35);
		tfPasswd.setBounds(115, 135, 155, 30);

		lbImage.setBounds(0, 0, 330, 80);
		lbImage.setBackground(Color.WHITE);
		
		btLogin.setBounds(60, 190, 70, 30);
		btReg.setBounds(200, 190, 70, 30);
//		btCancel.setBounds(230, 180, 70, 30);
		
		Image img = Toolkit.getDefaultToolkit().getImage(".\\img\\vcdlogo.png");
		lbImage.setIcon(new ImageIcon(img));
	}

	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		this.add(lbImage);
		this.add(lbUser);
		this.add(tfUser);
		
		this.add(lbPasswd);
		this.add(tfPasswd);
		
		this.add(btLogin);
		this.add(btReg);
//		this.add(btCancel);
	}

	@Override
	public void frameMain() {
		// TODO Auto-generated method stub
		Image img = Toolkit.getDefaultToolkit().getImage(".\\img\\supervcd.png");
		this.setTitle("SuperVCD-x");
		this.setIconImage(img);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 400, 330, 275);
		this.setResizable(false);
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		this.dispose();
	}
	
	public void register() {
		new Register(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginFrame();
	}

}
