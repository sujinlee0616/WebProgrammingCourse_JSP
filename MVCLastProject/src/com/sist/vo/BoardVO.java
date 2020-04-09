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
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
	private String filename;
	private String filesize;
	private int type;
}
