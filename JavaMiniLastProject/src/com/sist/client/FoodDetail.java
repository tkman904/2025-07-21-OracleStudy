package com.sist.client;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.net.*;

import com.sist.commons.ImageChange;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.JjimVO;

public class FoodDetail extends JPanel implements ActionListener {
	ControllerPanel cp;
	JLabel mainLa;
	JLabel[] las=new JLabel[9];
	JLabel[] lap=new JLabel[9];
	JTextPane ta;
	JButton b1, b2, b3;
	static int type=0;
	int fno=0;
	
	public FoodDetail(ControllerPanel cp) {
		setLayout(null);
		this.cp=cp;
		
		mainLa=new JLabel("");
		String[] temp= {"업체명", "주소", "전화", "음식종류",
				"영업시간", "주차", "평점", "가격대", "테마"};
		mainLa.setBounds(200, 15, 300, 350);
		mainLa.setBorder(new LineBorder(Color.red));
		add(mainLa);
		
		for(int i=0;i<las.length;i++) {
			las[i]=new JLabel(temp[i]);
			las[i].setBounds(510, i*35+15, 80, 30);
			add(las[i]);
			
			lap[i]=new JLabel("");
			lap[i].setBounds(600, i*35+15, 300, 30);
			add(lap[i]);
		}
		ta=new JTextPane();
		ta.setEnabled(false);
		JScrollPane js=new JScrollPane(ta);
		js.setBounds(200, 370, 850, 150);
		add(js);
		
		b1=new JButton("찜하기");
		b2=new JButton("좋아요");
		b3=new JButton("목록");
		JPanel p=new JPanel();
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.setBounds(590, 330, 390, 35);
		add(p);
		
		b3.addActionListener(this);
		b1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b3) {
			if(type==0) {
				cp.card.show(cp, "HF");
				cp.hf.init();
				cp.hf.print();
			}
			else {
				cp.card.show(cp, "FF");
				cp.ff.print();
			}
		}
		else if(e.getSource()==b1) {
			FoodDAO dao=FoodDAO.newInstance();
			JjimVO vo=new JjimVO();
			vo.setFno(fno);
			vo.setId(cp.myId);
			int res=dao.jjimInsert(vo);
			if(res>0) {
				JOptionPane.showMessageDialog(this, "찜 완료!!");
				print(fno);
			}
		}
	}
	
	public void print(int fno) {
		this.fno=fno;
		
		FoodDAO dao=FoodDAO.newInstance();
		FoodVO vo=dao.foodDetailData(fno);
		lap[0].setText(vo.getName());
		lap[1].setText(vo.getAddress());
		lap[2].setText(vo.getPhone());
		lap[3].setText(vo.getType());
		lap[4].setText(vo.getTime());
		lap[5].setText(vo.getParking());
		lap[6].setText(String.valueOf(vo.getScore()));
		lap[7].setText(vo.getPrice());
		lap[8].setText(vo.getTheme());
		ta.setText(vo.getContent());
		
		try {
			URL url=new URL(vo.getPoster());
			Image img=ImageChange.getImage(new ImageIcon(url), 300, 350);
			mainLa.setIcon(new ImageIcon(url));
		} catch(Exception ex) {}
		
		int count=dao.jjimCheck(fno, cp.myId);
		
		if(count==0) {
			b1.setEnabled(true);
		} 
		else {
			b1.setEnabled(false);
		}
	}
}
