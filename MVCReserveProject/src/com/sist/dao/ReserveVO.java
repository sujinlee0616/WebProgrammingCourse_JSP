package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/* [Reserve 테이블]
 *  rno NUMBER,
    id VARCHAR2(20),
    mno NUMBER,
    tname VARCHAR2(100),
    rdate VARCHAR2(100),
    rtime VARCHAR2(100),
    rinwon VARCHAR2(20),
    rprice VARCHAR2(20),
    isReserve NUMBER
 */
@Getter
@Setter
public class ReserveVO {
	private int rno;
	private String id;
	private int mno;
	private String tname;
	private String rdate;
	private String rtime;
	private String rinwon;
	private String rprice;
	private int isReserve;
}
