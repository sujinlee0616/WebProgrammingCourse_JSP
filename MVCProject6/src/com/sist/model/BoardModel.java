package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller // 등록(메모리 할당) 
public class BoardModel {
	@RequestMapping("board/list.do") // request와 매핑
	public String board_list(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("msg", "게시판"); // (key,value)
		return "list.jsp";
	}
}
