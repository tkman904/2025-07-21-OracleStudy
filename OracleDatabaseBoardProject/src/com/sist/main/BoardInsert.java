package com.sist.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class BoardInsert extends JPanel implements ActionListener {
	JLabel la1, la2, la3, la4, la5;
	JTextField tf1, tf2;
	JTextArea ta;
	JPasswordField pf;
	JButton b1, b2;
	private BoardMainFrame bm;
	
	public BoardInsert(BoardMainFrame bm) {
		this.bm=bm;
		la1=new JLabel("글쓰기", JLabel.CENTER);
		la1.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		
		la2=new JLabel("이름", JLabel.CENTER);
		la3=new JLabel("제목", JLabel.CENTER);
		la4=new JLabel("내용", JLabel.CENTER);
		la5=new JLabel("비밀번호", JLabel.CENTER);
		
		tf1=new JTextField();
		tf2=new JTextField();
		pf=new JPasswordField();
		
		ta=new JTextArea();
		JScrollPane js=new JScrollPane(ta);
		
		b1=new JButton("글쓰기");
		b2=new JButton("취소");
		
		setLayout(null);
		la1.setBounds(10, 15, 610, 50);
		add(la1);
		
		la2.setBounds(30, 75, 80, 30);
		tf1.setBounds(115, 75, 100, 30);
		add(la2);
		add(tf1);
		
		la3.setBounds(30, 110, 80, 30);
		tf2.setBounds(115, 110, 450, 30);
		add(la3);
		add(tf2);
		
		la4.setBounds(30, 145, 80, 30);
		js.setBounds(115, 145, 450, 150);
		add(la4);
		add(js);
		
		la5.setBounds(30, 300, 80, 30);
		pf.setBounds(115, 300, 100, 30);
		add(la5);
		add(pf);
		
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		p.setBounds(50, 340, 535, 35);
		add(p);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b1) {
			String name=tf1.getText();
			if(name.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요");
				tf1.requestFocus();
				return;
			}
			
			String subject=tf2.getText();
			if(subject.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "제목을 입력하세요");
				tf2.requestFocus();
				return;
			}
			
			String content=ta.getText();
			if(content.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "내용을 입력하세요");
				ta.requestFocus();
				return;
			}
			
			String pwd=String.valueOf(pf.getPassword());
			if(pwd.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				pf.requestFocus();
				return;
			}
			
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO dao=BoardDAO.newInstance();
			dao.boardInsert(vo);
			
			bm.card.show(bm.getContentPane(), "list");
			bm.bList.print();
		}
		else if(e.getSource()==b2) {
			bm.card.show(bm.getContentPane(), "list");
		}
	}
}
