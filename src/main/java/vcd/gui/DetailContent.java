package vcd.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vcd.db.action.AlbumDataPool;
import vcd.frame.Framework;

/**
* @Author: PQ  
* @Data: 2018年8月24日上午10:29:54 
*/
public class DetailContent extends JDialog implements Framework{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义窗口需要的部件
	private JLabel lb;
	private DefaultTableModel mdl;
	private JTable tb;
	private JScrollPane jsp;
	private JPanel pl;
	
	private JButton ensure;
	//打开数据池
	AlbumDataPool adp = AlbumDataPool.getPool();
	
	public DetailContent(JFrame jf, String[] collect) {
		
		super(jf,true);
		frameMain();
		frameInitial();
		frameAction();
		frameLocate();
		frameAdd();
	
		//获取数据
		adp.showMusic(mdl, collect);
		
		this.setVisible(true);
		
	}

	@Override
	public void frameMain() {
		// TODO Auto-generated method stub
		this.setTitle("详细信息");
		this.setBounds(500, 500, 300, 400);
		this.setResizable(false);
	}

	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
		lb = new JLabel("歌曲信息");
		
		Object[] header = {"歌名","时长"};
		Object[][] data = null;
		
		mdl = new DefaultTableModel(data, header);
		tb = new JTable(mdl) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		jsp = new JScrollPane(tb);
		pl = new JPanel();
		
		ensure = new JButton("确定");
		
		tb.getTableHeader().setReorderingAllowed(false);
		tb.setRowHeight(20);
	}

	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		ensure.addActionListener(new ActionListener() {
			
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
		//设定位置
		lb.setBounds(120, 10, 70, 35);
		pl.setBounds(15, 50, 260, 270);
		ensure.setBounds(110, 330, 70, 30);
	}

	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
		
		pl.setLayout(new BorderLayout());
		pl.add(jsp);
		
		//添加主窗口
		this.setLayout(null);
		this.add(lb);
		this.add(pl);
		this.add(ensure);
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		this.dispose();
	}
}
