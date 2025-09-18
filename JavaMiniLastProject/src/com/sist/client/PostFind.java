package com.sist.client;
// 우편번호 검색
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class PostFind extends JFrame {
	JLabel la;
	JTextField tf;
	JButton btn;
	JTable table;
	DefaultTableModel model;
	
	public PostFind() {
		la=new JLabel("동/읍/면 입력: ");
		tf=new JTextField(10);
		btn=new JButton("검색");
		
		JPanel p=new JPanel();
		p.add(la);
		p.add(tf);
		p.add(btn);
		add("North", p);
		String[] col= {"우편번호", "주소"};
		String[][] row=new String[0][2];
		model=new DefaultTableModel(row, col) {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table=new JTable(model);
		JScrollPane js=new JScrollPane(table);
		add("Center", js);
		setSize(350, 300);
	}
}
