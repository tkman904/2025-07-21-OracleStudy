package com.sist.vo;

import java.util.*;
import lombok.Data;

@Data
public class EmpVO {
	private int empno,mgr,sal,deptno,comm;
	private String ename,job;
	private Date hiredate;
}
