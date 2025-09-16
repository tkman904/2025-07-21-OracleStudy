package com.sist.client;
import javax.swing.*;
import java.awt.*; // 배치 => 레이아웃
import java.awt.event.*; // 이벤트 처리
public class ClientMainFrame extends JFrame
implements ActionListener {
	
	MenuForm menu=new MenuForm();
	ControllerPanel cp=new ControllerPanel();
	Login login=new Login();
	Join join=new Join();
	// has-a => 포함 클래스
	public ClientMainFrame() {
		setLayout(null);
		menu.setBounds(135, 15, 1000, 50);
		cp.setBounds(20, 85, 1230, 680);
		add(menu);
		add(cp);
		setSize(1280, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.b1.addActionListener(this);
		menu.b5.addActionListener(this);
		menu.b6.addActionListener(this);
		menu.b3.addActionListener(this);
		
		login.b1.addActionListener(this); // 로그인
		login.b2.addActionListener(this); // 회원가입
		login.b3.addActionListener(this); // 취소
		
		join.b1.addActionListener(this); // 회원가입
		join.b2.addActionListener(this); // 취소
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		}catch(Exception e) {}
		
		new ClientMainFrame(); // 생성자 호출
		// ClientMainFrame c=new ClientMainFrame(); => 객체 생성 불필요해서 이렇게 쓰면 안된다
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==menu.b1) {
			cp.card.show(cp, "HF");
		}
		else if(e.getSource()==menu.b3) {
			cp.card.show(cp, "FF");
		}
		else if(e.getSource()==menu.b5) {
			cp.card.show(cp, "CF");
		}
		else if(e.getSource()==menu.b6) {
			cp.card.show(cp, "BF");
		}
		else if(e.getSource()==login.b2) {
			login.setVisible(false);
			join.setVisible(true);
		}
		else if(e.getSource()==login.b3) {
			dispose();
			System.exit(0);
		}
		else if(e.getSource()==join.b1) {
			
		}
		else if(e.getSource()==join.b2) {
			login.setVisible(true);
			join.setVisible(false);
		}
	}

}
