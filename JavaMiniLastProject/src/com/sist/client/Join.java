package com.sist.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Join extends JFrame {
	JLabel[] las=new JLabel[9];
	String[] title= {"ID", "비밀번호", "이름", "성별", "우편번호",
					"주소", "상세주소", "전화", "소개"};
	JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	JPasswordField pf;
	JTextArea ta;
	JRadioButton rb1, rb2;
	JButton b1, b2, b3, b4;
	
	public Join() {
		setLayout(null);
		for(int i=0;i<las.length;i++) {
			las[i]=new JLabel(title[i]);
			las[i].setBounds(10, 15+(i*35), 65, 30);
			add(las[i]);
		}
		
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		tf4=new JTextField();
		tf5=new JTextField();
		tf6=new JTextField();
		b3=new JButton("중복체크");
		b4=new JButton("주소검색");
		tf1.setBounds(80, 15, 200, 30);
		b3.setBounds(290, 15, 90, 30);
		add(tf1);
		add(b3);
		
		pf=new JPasswordField();
		pf.setBounds(80, 50, 200, 30);
		add(pf);
		
		tf2.setBounds(80, 85, 200, 30);
		add(tf2);
		
		tf3.setBounds(80, 155, 200, 30);
		b4.setBounds(290, 155, 90, 30);
		add(tf3);
		add(b4);
		
		tf4.setBounds(80, 190, 300, 30);
		add(tf4);
		
		tf5.setBounds(80, 225, 300, 30);
		add(tf5);
		
		tf6.setBounds(80, 260, 200, 30);
		add(tf6);
		
		rb1=new JRadioButton("남자");
		rb2=new JRadioButton("여자");
		ButtonGroup bg=new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		rb1.setBounds(80, 120, 70, 30);
		rb2.setBounds(155, 120, 70, 30);
		add(rb1);
		add(rb2);
		
		b1=new JButton("회원가입");
		b2=new JButton("취소");
		
		ta=new JTextArea();
		JScrollPane js=new JScrollPane(ta);
		js.setBounds(80, 295, 300, 100);
		add(js);
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		p.setBounds(10, 405, 370, 35);
		add(p);
		
		tf1.setEnabled(false);
		tf3.setEnabled(false);
		tf4.setEnabled(false);
		rb1.setSelected(true);
		
		setSize(400, 485);
		//setVisible(true);
	}
}
