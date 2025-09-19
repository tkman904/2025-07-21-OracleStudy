package com.sist.client;
import javax.swing.*;

import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;
import com.sist.vo.ZipcodeVO;

import java.awt.*; // 배치 => 레이아웃
import java.awt.event.*; // 이벤트 처리
import java.util.List;
public class ClientMainFrame extends JFrame
implements ActionListener {
	
	MenuForm menu=new MenuForm();
	ControllerPanel cp=new ControllerPanel();
	Login login=new Login();
	Join join=new Join();
	IdCheck ic=new IdCheck();
	PostFind post=new PostFind();
	JMenuItem genie;
	JMenuItem melon;
	JMenuItem user;
	// has-a => 포함 클래스
	public ClientMainFrame() {
		JMenuBar bar=new JMenuBar();
		JMenu menu1=new JMenu("기타");
		genie=new JMenuItem("지니뮤직");
		melon=new JMenuItem("멜론");
		user=new JMenuItem("개인");
		menu1.add(genie);
		menu1.add(melon);
		menu1.add(user);
		bar.add(menu1);
		setJMenuBar(bar);
		
		setLayout(null);
		menu.setBounds(135, 15, 1000, 50);
		cp.setBounds(20, 85, 1230, 680);
		add(menu);
		add(cp);
		setSize(1280, 800);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu.b1.addActionListener(this);
		menu.b5.addActionListener(this);
		menu.b6.addActionListener(this);
		menu.b3.addActionListener(this);
		menu.b2.addActionListener(this);
		menu.b7.addActionListener(this);
		
		login.b1.addActionListener(this); // 로그인
		login.b2.addActionListener(this); // 회원가입
		login.b3.addActionListener(this); // 취소
		
		join.b1.addActionListener(this); // 회원가입
		join.b2.addActionListener(this); // 취소
		join.b3.addActionListener(this); // 아이디 체크
		join.b4.addActionListener(this); // 주소검색
		
		genie.addActionListener(this);
		
		//아이디 체크
		ic.ok.addActionListener(this); // 확인
		ic.check.addActionListener(this); // 검색
		
		post.btn.addActionListener(this); // 주소검색
		post.tf.addActionListener(this); // 주소입력 후 엔터
		post.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==post.table) {
					if(e.getClickCount()==2) {
						int row=post.table.getSelectedRow();
						String zip=post.model.getValueAt(row, 0).toString();
						String addr=post.model.getValueAt(row, 1).toString();
						
						join.tf3.setText(zip);
						join.tf4.setText(addr);
						post.setVisible(false);
					}
				}
			}
		});
		// TextField / Button / MenuItem => ActionListener
		// Table / Label / Image / Panel => MouseListener
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
		else if(e.getSource()==menu.b2) {
			cp.card.show(cp, "MF");
			cp.mf.print();
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
		else if(e.getSource()==menu.b7) {
			cp.card.show(cp, "NN");
			cp.nn.newsPrint("맛집");
		}
		else if(e.getSource()==login.b1) {
			String id=login.tf.getText();
			if(id.trim().length()<1) {
				login.tf.requestFocus();
				return;
			}
			String pwd=new String(login.pf.getPassword());
			if(pwd.trim().length()<1) {
				login.pf.requestFocus();
				return;
			}
			MemberDAO dao=MemberDAO.newInstance();
			MemberVO vo=dao.isLogin(id, pwd);
			
			if(vo.getMsg().equals("NOID")) {
				JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다");
				login.tf.setText("");
				login.pf.setText("");
				login.tf.requestFocus();
			}
			else if(vo.getMsg().equals("NOPWD")) {
				JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다");
				login.pf.setText("");
				login.pf.requestFocus();
			}
			else {
				login.setVisible(false);
				setVisible(true);
				setTitle(vo.getName());
				cp.myId=id;
			}
		}
		else if(e.getSource()==login.b2) {
			login.setVisible(false);
			join.setVisible(true);
		}
		else if(e.getSource()==login.b3) {
			dispose();
			System.exit(0);
//			setVisible(true);
//			login.setVisible(false);
		}
		else if(e.getSource()==join.b1) {
			String id=join.tf1.getText();
			if(id.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "아이디 중복체크를 하세요");
				return;
			}
			String pwd=String.valueOf(join.pf.getPassword());
			String name=join.tf2.getText();
			String sex="";
			if(join.rb1.isSelected()) {
				sex="남자";
			}
			else {
				sex="여자";
			}
			String zip=join.tf3.getText();
			String addr1=join.tf4.getText();
			String addr2=join.tf5.getText();
			String phone=join.tf6.getText();
			String content=join.ta.getText();
			
			MemberVO vo=new MemberVO();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setName(name);
			vo.setSex(sex);
			vo.setPost(zip);
			vo.setAddr1(addr1);
			vo.setAddr2(addr2);
			vo.setPhone(phone);
			vo.setContent(content);
			
			MemberDAO dao=MemberDAO.newInstance();
			int res=dao.memberJoin(vo);
			if(res==0) {
				JOptionPane.showMessageDialog(this, "회원가입에 실패하셨습니다");
			}
			else {
				JOptionPane.showMessageDialog(this, "회원가입을 축하합니다");
				join.setVisible(false);
				login.setVisible(true);
			}
		}
		else if(e.getSource()==join.b2) {
			dispose();
			System.exit(0);
		}
		else if(e.getSource()==join.b3) {
			ic.tf.setText("");
			ic.rla.setText("");
			ic.setVisible(true);
		}
		else if(e.getSource()==join.b4) {
			for(int i=post.model.getRowCount()-1;i>=0;i--) {
				post.model.removeRow(i);
			}
			post.tf.setText("");
			post.setVisible(true);
		}
		else if(e.getSource()==genie) {
			cp.card.show(cp, "GM");
		}
		// 아이디 체크
		else if(e.getSource()==ic.check) {
			String id=ic.tf.getText();
			// request.getParameter()
			if(id.trim().length()<1) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
				// alert()
				ic.tf.requestFocus();
				return;
			}
			MemberDAO dao=MemberDAO.newInstance();
			int count=dao.memberIdCheck(id.trim());
			if(count==0) { // ID가 없는 상태
				ic.rla.setBackground(Color.blue);
				ic.rla.setText(id+"는(은) 사용 가능한 아이디입니다");
				ic.ok.setVisible(true);
			}
			else { // ID가 있는 상태
				ic.rla.setBackground(Color.red);
				ic.rla.setText(id+"는(은) 이미 사용중인 아이디입니다");
			}
		}
		// 결과
		else if(e.getSource()==ic.ok) {
			String id=ic.tf.getText();
			join.tf1.setText(id);
			ic.setVisible(false);
		}
		// 우편번호 검색
		else if(e.getSource()==post.btn || e.getSource()==post.tf) {
			String fd=post.tf.getText();
			if(fd.trim().length()<1) {
				post.tf.requestFocus();
				return;
			}
			MemberDAO dao=MemberDAO.newInstance();
			int count=dao.postFindCount(fd);
			if(count==0) {
				JOptionPane.showMessageDialog(this, "검색결과가 없습니다");
				post.tf.setText("");
				post.tf.requestFocus();
			}
			else {
				List<ZipcodeVO> list=dao.postFind(fd);
				for(ZipcodeVO vo : list) {
					String[] data= {vo.getZipcode(), vo.getAddress()};
					post.model.addRow(data);
				}
			}
		}
	}

}
