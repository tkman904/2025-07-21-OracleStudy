package com.sist.vo;
/*
 * ID      NOT NULL  VARCHAR2(20)  
 * PWD               VARCHAR2(10)  
 * NAME              VARCHAR2(52)  
 * SEX               CHAR(6)       
 * POST              VARCHAR2(7)   
 * ADDR1             VARCHAR2(500) 
 * ADDR2             VARCHAR2(100) 
 * PHONE             VARCHAR2(13)  
 * CONTENT           CLOB          
 * REGDATE           DATE      
 */
import java.util.Date;
import lombok.Data;

@Data
public class MemberVO {
	private String id, pwd, name, sex, post, addr1, addr2, phone, content,msg;
	private Date regdate;
}
