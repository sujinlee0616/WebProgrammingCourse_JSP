package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.temp.Controller;

@Controller // 메모리 할당 
public class HomeModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "홈페이지");
		request.setAttribute("main_jsp", "home.jsp");
		return "main.jsp";  // home.jsp는 main.jsp와 같은 위치에 있으므로 "../main/xx.jsp"로 코딩하지X
	}

}
