package com.sist.dao;
/*
 *   매핑
 *     => 테이블 : VO
 *     => 테이블 : DAO
 *               ----- 통합 (BI) = Service
 *               게시판 / 댓글 
 *     -----------------------------------
 *       DAO vs Service 차이점
 */

import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.db.*;

public class StudentDAO {
	private static StudentDAO dao;
	private CreateDataBase db=new CreateDataBase();
	
	// 싱글턴
	public static StudentDAO newInstance() {
		if(dao==null)
			dao=new StudentDAO();
		return dao;
	}
	
	public List<StudentVO> stdAllData() {
		List<StudentVO> list=new ArrayList<StudentVO>();
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=db.getConnection();
			String sql="SELECT hakbun, name, kor, eng, math,"
					+ "kor+eng+math,ROUND((kor+eng+math)/3.0, 2) "
					+ "FROM student";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				StudentVO vo=new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				vo.setTotal(rs.getInt(6));
				vo.setAvg(rs.getDouble(7));
				list.add(vo);		
			}
			rs.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			db.disConnection(conn, ps);
		}
		return list;
	}
	
	public void stdInsert(StudentVO vo) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=db.getConnection();
			String sql="INSERT INTO student(hakbun, name, kor, eng, math) "
					+ "VALUES((SELECT NVL(MAX(hakbun)+1, 100) FROM student),"
					+ "?, ?, ?, ?)"; // ;은 자동 추가
			// SQL Injection
			// PreparedStatement => 미리 SQL을 전송 => 나중에 값을 채워서 실행
			ps=conn.prepareStatement(sql);
			// ? 에 값을 채운다
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getKor());
			ps.setInt(3, vo.getEng());
			ps.setInt(4, vo.getMath());
			//실행 요청
			ps.executeUpdate(); // insert / update / delete => commit()추가
			// ps.executeQuery() // select
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			db.disConnection(conn, ps);
		}
	}
}
