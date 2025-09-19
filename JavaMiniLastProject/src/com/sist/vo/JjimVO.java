package com.sist.vo;

import lombok.Data;

/*
 *  JNO NOT NULL NUMBER
 *  FNO NUMBER
 *  ID VARCHAR2(20)
 */
@Data
public class JjimVO {
	private int jno;
	private int fno;
	private String id;
	private String poster;
	private String name;
}
