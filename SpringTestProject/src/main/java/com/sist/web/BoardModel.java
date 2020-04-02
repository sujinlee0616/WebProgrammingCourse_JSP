package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardModel {

	@RequestMapping("main/board.do")
	public String main_board(HttpServletRequest request)
	{
		request.setAttribute("msg","Board...");
		return "board"; // main/board.jsp 라고 코딩할 필요 X. 이미 applicationContext.xml에 p:prefix, p:sufix로 설정되어 있음
	}
}
