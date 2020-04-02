package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 메모리 할당 
public class HomeModel {	
	@RequestMapping("main/home.do") // 사용자가 'main/home.do' 주면 아래의 메소드를 호출해라. 
	public String home_main(HttpServletRequest request)
	{
		request.setAttribute("msg", "Home...");
		return "home";	// main/home.jsp 라고 코딩할 필요 X. 이미 applicationContext.xml에 p:prefix, p:sufix로 설정되어 있음 
	}
}


