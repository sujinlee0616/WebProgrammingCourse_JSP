// 이 VO 하나를 게시판 3개 다 쓸거임.
package com.sist.vo; 

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	
	private String dbday; // 프로시져에서 dbday 만들었었음 ==> VO에 추가해주자.
	// VO에는, 테이블의 컬럼이 아니더라도, 데이터 전송해야하는거라면 들어올 수 있음 
	
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
	private String filename;
	private String filesize;
	private int type;
}
