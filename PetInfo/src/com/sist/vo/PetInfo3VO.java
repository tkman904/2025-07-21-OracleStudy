package com.sist.vo;

import lombok.Data;

/*
 *  NO       NOT NULL NUMBER        
 *  TITLE    NOT NULL VARCHAR2(300) 
 *  POSTER   NOT NULL VARCHAR2(300) 
 *  CATEGORY          VARCHAR2(200) 
 *  PREVIEW  NOT NULL VARCHAR2(500)
 */
@Data
public class PetInfo3VO {
	private int no;
	private String title, poster, category, preview;
}
