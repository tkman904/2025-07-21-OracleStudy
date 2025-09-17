package com.sist.vo;

import lombok.Data;

/*
 *   NO     NOT NULL NUMBER        
 *   TITLE  NOT NULL VARCHAR2(200) 
 *   POSTER NOT NULL VARCHAR2(260) 
 */
@Data
public class PetInfoVO {
	private int no;
	private String title, poster;
}
