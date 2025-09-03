package com.sist.vo;

import lombok.Data;
/*
 * FNO      NUMBER(38)
 * NAME     VARCHAR2(4000)
 * TYPE     VARCHAR2(4000)
 * PHONE    VARCHAR2(1000)
 * ADDRESS  VARCHAR2(4000)
 * SCORE    NUMBER(38,1)
 * THEME    VARCHAR2(4000)
 * POSTER   VARCHAR2(4000)
 * IMAGES   VARCHAR2(4000)
 * TIME     VARCHAR2(1000)
 * PARKING  VARCHAR2(1000)
 * CONTENT  VARCHAR2(4000)
 * HIT 	    NUMBER(38)
 * PRICE 	VARCHAR2(1000)
 */
@Data
public class FoodVO {
	private int fno,hit;
	private double score;
	private String name,type,phone,address,theme,poster,images,time,parking,content,price;
}
