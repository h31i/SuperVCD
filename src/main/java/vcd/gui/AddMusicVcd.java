package vcd.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vcd.db.action.AlbumDataPool;
import vcd.frame.Framework;

public class AddMusicVcd extends JDialog implements Framework {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//添加组件
	private JLabel albumName,areaName,singerName,songNum,imgName;
	private JTextField textAlbum,textArea,textSinger,textSongn,textImg;
	private JButton bnSave, bnModify, bnCancel, bnSubmit;
	
	//表格组件
	private JTable tb;
	private JScrollPane jsp;
	private DefaultTableModel md;
	private JPanel panelTable;
	
	//在此处定义表格数据方便处理
	Object[][] data = null;
	
	//创建与专辑信息的接口
	AlbumDataPool adp = AlbumDataPool.getPool();
	
	public AddMusicVcd() {
		frameMain();
		frameInitial();
		frameAction();
		frameLocate();
		frameAdd();
		
		this.setVisible(true);
	}
	
	@Override
	public void frameMain() {
		/* TODO Auto-generated method stub */
		this.setTitle("添加VCD");
		this.setBounds(520, 280, 530, 400);
		this.setResizable(false);
	}

	@Override
	public void frameInitial() {
		// TODO Auto-generated method stub
		albumName = new JLabel("专辑名");
		singerName = new JLabel("歌手");
		areaName = new JLabel("地区");
		songNum = new JLabel("歌曲数量");
		imgName = new JLabel("图片路径");
		
		
		textAlbum = new JTextField();
		textArea = new JTextField();
		textSinger = new JTextField();
		textSongn = new JTextField();
		textImg = new JTextField();
		
		bnSave = new JButton("确定");
		bnModify = new JButton("修改");
		bnCancel = new JButton("取消");
		bnSubmit = new JButton("提交");
		
		bnSubmit.setEnabled(false);
		
		Object[] headers = {"歌名","时长"};
		
		
		md = new DefaultTableModel(data, headers);
		tb = new JTable(md);
		jsp = new JScrollPane(tb);
		tb.setRowHeight(26);
		//设置整列不能移动
		tb.getTableHeader().setReorderingAllowed(false);
		panelTable = new JPanel();
	}

	@Override
	public void frameAction() {
		// TODO Auto-generated method stub
		bnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = 0 ;
				
				try {
					n = Integer.valueOf(textSongn.getText());
					toClock(false);
					mdlReflush(n);
					bnSave.setEnabled(false);
					JOptionPane.showMessageDialog(null, "请将右边表格填写完成后提交");
					bnSubmit.setEnabled(true);
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "歌曲数只能为数字");
					textSongn.setText("");
//					toClock(false);
					bnSave.setEnabled(true);
				}
			}
		});
		
		bnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				toClock(true);
				bnSave.setEnabled(true);
				bnSubmit.setEnabled(false);
			}
		});
		
		bnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				toExit();
			}
		});
		
		bnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addVcd();
				JOptionPane.showMessageDialog(null, "保存成功");
				mdlReflush(0);
				toSetNull();
			}
		});
	}

	@Override
	public void frameLocate() {
		// TODO Auto-generated method stub
		albumName.setBounds(40, 20, 70, 35);
		textAlbum.setBounds(120, 20, 150, 35);

		singerName.setBounds(40, 70, 70, 35);
		textSinger.setBounds(120, 70, 150, 35);

		areaName.setBounds(40, 120, 70, 35);
		textArea.setBounds(120, 120, 150, 35);
		
		imgName.setBounds(40, 170, 70, 35);
		textImg.setBounds(120, 170, 150, 35);

		songNum.setBounds(40, 220, 70, 35);
		textSongn.setBounds(120, 220, 150, 35);
		
		bnSave.setBounds(40, 275, 60, 30);
		bnModify.setBounds(125, 275, 60, 30);
		bnCancel.setBounds(210, 275, 60, 30);
		bnSubmit.setBounds(40, 320, 230, 30);
		
		panelTable.setBounds(300, 20, 200, 330);
	}

	@Override
	public void frameAdd() {
		// TODO Auto-generated method stub
		
		this.setLayout(null);
		
		this.add(albumName);
		this.add(textAlbum);
		
		this.add(singerName);
		this.add(textSinger);
		
		this.add(areaName);
		this.add(textArea);
		
		this.add(songNum);
		this.add(textSongn);
		
		this.add(imgName);
		this.add(textImg);
		
		this.add(bnSave);
		this.add(bnModify);
		this.add(bnCancel);
		this.add(bnSubmit);
		
		panelTable.setLayout(new BorderLayout());
		panelTable.add(jsp);
		
		this.add(panelTable);
	}

	@Override
	public void toExit() {
		// TODO Auto-generated method stub
		this.dispose();
	}
	
	private void toClock(Boolean ch) {
		textAlbum.setEditable(ch);
		textArea.setEditable(ch);
		textSinger.setEditable(ch);
		textSongn.setEditable(ch);
		textImg.setEditable(ch);
	}
	private void toSetNull() {
		textAlbum.setText("");
		textArea.setText("");
		textSinger.setText("");
		textSongn.setText("");
		textImg.setText("");
	}
	
	private void mdlReflush(int num) {
		String[] row = {"",""};
		while(md.getRowCount() != 0) {
			md.removeRow(md.getRowCount()-1);
		}
		
		while(num > 0) {
			md.addRow(row);
			num--;
		}
	}
	
	private void addVcd() {
		int n = md.getRowCount();
		String[] albumData = {
				textSinger.getText(),
				textAlbum.getText(),
				textArea.getText(),
				textImg.getText(),
				textSongn.getText()};
		String[][] musicData = new String[n][2];
		
		for(int i = 0;i < n;i++) {
			musicData[i][0] = (String) md.getValueAt(i, 0);
			musicData[i][1] = (String) md.getValueAt(i, 1);
		}
		System.out.println(Arrays.deepToString(musicData));
		
		adp.poolAdd(albumData, musicData);
		adp.savePool();
	}
	
//	public static void main(String[] args) {
//		new AddMusicVcd();
//	}

}
