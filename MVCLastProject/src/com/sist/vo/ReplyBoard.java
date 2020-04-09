// 댓글형 게시판의 댓글. 대댓글 기능은 안 만들거임.
package com.sist.vo;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReplyBoard {
	private int no;
	private int bno;
	private String id;
	private String name;
	private String msg;
	private Date regdate;
	
}
