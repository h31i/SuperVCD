package vcd.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import vcd.db.action.AlbumDataPool;
import vcd.frame.Framework;

/**
* @Author: PQ  
* @Date: 2018年8月24日下午10:08:51 
*/
public class RightClick extends JPopupMenu implements Framework{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem delete;//, copy;
	private DefaultTableModel model;
	private int rowNum;
	
	AlbumDataPool adp = AlbumDataPool.getPool();
	
	public RightClick(DefaultTableModel mdl,int row) {
		this.model = mdl;
		this.rowNum = row;
		frameMain();
		frameInitial();
		frameAction();
		frameLocate();
		frameAdd();
	}

	@Override
	public void frameMain() {
		// TODO Auto-generated method stub
	
		this.setFont(new Font("",1,18));
	}

	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
		delete = new JMenuItem("删除");
//		copy = new JMenuItem("复制");
		
		delete.setPreferredSize(new Dimension(100, 30));
	}

	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null, "删除成功");
				int n = JOptionPane.showConfirmDialog(null, "确定要删除吗？");
				System.out.println(n);
				if(n == 0) {
					adp.remove(model, rowNum);
				}
			}
		});
	}

	@Override
	public void frameLocate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
//		this.add(copy);
		this.add(delete);
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		
	}

}
