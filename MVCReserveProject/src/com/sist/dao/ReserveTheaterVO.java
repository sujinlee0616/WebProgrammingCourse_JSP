package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*  [ReserveTheater 테이블]
 *  tno NUMBER,
    tname VARCHAR2(100),
    tloc VARCHAR2(100),
    tdate VARCHAR2(100)  
 */
@Getter
@Setter
public class ReserveTheaterVO {
	private int tno;
	private String tname;
	private String tloc;
	private String tdate;
	
	
}
