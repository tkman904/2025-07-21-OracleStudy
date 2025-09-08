package com.sist.vo;

import lombok.Data;

@Data
public class StudentVO {
	private int hakbun;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double avg;
}
