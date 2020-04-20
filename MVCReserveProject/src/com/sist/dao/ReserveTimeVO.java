package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/* [ReserveTime 테이블]  
 * 
 *  tno NUMBER,
    time VARCHAR2(20)
 */
@Getter
@Setter
public class ReserveTimeVO {
	private int tno;
	private String time;
}
