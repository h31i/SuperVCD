package vcd.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vcd.db.action.AlbumDataPool;
import vcd.frame.Framework;

public class HomePage extends JFrame implements Framework {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//创建顶部水平菜单栏
	private JMenuBar topBar;
	//添加菜单
	private JMenu itemFileBar;
	//添加菜单栏项
	private JMenuItem itemUserReset;
//	private JMenuItem itemVcdAdd;
	private JMenuItem itemLeave;
	private JMenuItem itemLoginOut;
	//添加组合框(下拉列表框)对应文本框和按钮
	private JComboBox<String> cbBox;
	private JTextField tfComboBox;
	private JButton btnToSearch;
	private JButton btnGetAll;
	//数据区域
	private JPanel plData;
	private JScrollPane scrollTable;
	private JTable foreTable;
	private DefaultTableModel model;

	AlbumDataPool adp = AlbumDataPool.getPool();
	
	public HomePage(){
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
		Image img = Toolkit.getDefaultToolkit().getImage(".\\img\\supervcd.png");
		this.setTitle("SuperVCD-2");
		this.setIconImage(img);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(520, 280, 800, 600);
		this.setResizable(false);
	}

	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
			topBar = new JMenuBar();
			itemFileBar = new JMenu("文件");
			itemUserReset = new JMenuItem("修改密码");
//			itemVcdAdd = new JMenuItem("添加VCD");
			itemLoginOut = new JMenuItem("重新登录");
			itemLeave = new JMenuItem("退出");
			
			String[] itemData = {"歌手","专辑","地区"};
			cbBox = new JComboBox<String>(itemData);
			tfComboBox = new JTextField(" 请根据要求输入你要查询的内容");
			btnToSearch = new JButton("搜索");
			btnGetAll = new JButton("全部");
			
			//表格区
			plData = new JPanel();
			plData.setBackground(Color.GRAY);
			//表格model设置
			Object[] headers = {"歌手","专辑","地区","信息","歌曲数量"};
			Object[][] tableData = null;
			
			model = new DefaultTableModel(tableData, headers);
			
			foreTable = new JTable(model) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row,int column) {
					return false;
				}
			};
			foreTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollTable = new JScrollPane(foreTable);
			
	}
		
	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		//修改密码
		itemUserReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modify();
			}
		});
		
		//注销此次登陆
		itemLoginOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LoginFrame();
				toExit();
			}
		});
		
		//退出程序
		itemLeave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				toExit();
			}
		});
		
		//自动清除文本框中的文本
		tfComboBox.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfComboBox.setText("");
			}
			
			
		});
		
		//搜索指定内容
		btnToSearch.addActionListener(new ActionListener() {
			String pointData;
			String searchData;
			int point;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pointData = (String) cbBox.getSelectedItem();
				searchData = tfComboBox.getText();
				
				if(pointData.equals("歌手")){
					point = 0;
				}else if(pointData.equals("专辑")) {
					point = 1;
				}else {
					point = 2;
				}
				
				if(searchData.equals("")) {
					adp.showTable(model);
				}else {
					adp.toSearch(model,point, searchData);					
				}
				
			}
		});
		
		//获取全部内容
		btnGetAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adp.showTable(model);
			}
		});
		
		//用于添加双击显示歌曲信息列表
		foreTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int clickCount = e.getClickCount();
				if (clickCount == 2) {
					showMusicDetail(foreTable.getSelectedRow());
				}else if(clickCount == 1 && e.getButton() == 3 ) {
					
					int row = foreTable.getSelectedRow();
					System.out.println(row);
					if(foreTable.isRowSelected(row)) {
						System.out.println(row);
						new RightClick(model, row).show(e.getComponent(),e.getX(),e.getY());						
					}else {
						JOptionPane.showMessageDialog(null, "请选择你要删除的内容");						
					}
				}
			}
		});
	}
	
	@Override
	public void frameLocate() {
		// TODO Auto-generated method stub
		cbBox.setBounds(30, 40, 90, 35);
		tfComboBox.setBounds(130, 40, 350, 35);
		btnToSearch.setBounds(490, 40, 70, 35);
		btnGetAll.setBounds(600, 40, 70, 35);
		
		//用于添加表格
		plData.setBounds(30, 90, 730, 440);
		
		//表格设置
		foreTable.setRowHeight(35);
		foreTable.setFont(new Font("", 1, 18));
		foreTable.getTableHeader().setReorderingAllowed(false);
	}
	
	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
		//菜单项add操作
		itemFileBar.add(itemUserReset);
		itemFileBar.addSeparator();
		itemFileBar.add(itemLoginOut);
		itemFileBar.addSeparator();
		itemFileBar.add(itemLeave);
		topBar.add(itemFileBar);
		
		//如果为管理员则显示这个数据
		topBar.add(new AdminMenu());
		
		//加上表格
		plData.setLayout(new BorderLayout());
		plData.add(scrollTable);
		
		//主框架add操作
		this.setLayout(null);
		this.setJMenuBar(topBar);
		this.add(cbBox);
		this.add(tfComboBox);
		this.add(btnToSearch);
		this.add(btnGetAll);
		this.add(plData);
		
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		this.dispose();
	}
	
	//双击显示专辑歌曲信息
	public void showMusicDetail(int n) {
		String[] getData = {(String) model.getValueAt(n, 0),(String) model.getValueAt(n, 1)};
		new DetailContent(this, getData);
	}
	//修改密码
	public void modify() {
		new ModifyPasswd(this);
	}
	
	//添加vcd
	public void addvcd() {
		new AddMusicVcd();
	}

//	public static void main(String[] args) {
//		new HomePage();
//	}
}
