package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/* [ReserveDate 테이블]
 *  tno NUMBER,
    time VARCHAR2(100)
*/
@Getter
@Setter
public class ReserveDateVO {

	private int tno;
	private String time;
}
