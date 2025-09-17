package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.sist.vo.*;

public class PetInfoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static PetInfoDAO dao;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 1. 드라이버 등록
	public PetInfoDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception ex) {}
	}
	
	// 2. 싱글턴
	public static PetInfoDAO newInstance() {
		if(dao==null)
			dao=new PetInfoDAO();
		return dao;
	}
	
	// 3. 연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL, "hr", "happy");
		} catch(Exception ex) {ex.printStackTrace();}
	}
	
	// 4. 해제
	public void disConnection() {
		try {
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch(Exception ex) {}
	}
	
	public void petinfoInsert(PetInfoVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO pet_info(no, title, poster) "
					+ "VALUES(pi_no_seq.nextval, ?, ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPoster());

			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
}
