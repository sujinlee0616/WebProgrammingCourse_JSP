package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.temp.Controller;

@Controller // 메모리 할당 
public class JoinModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "회원가입");
		request.setAttribute("main_jsp", "../member/join.jsp"); // request에다가 key:main_jsp, vlaue:../member/join.jsp 데이터를 싣는다  ==> main.jsp는 request에 실린 main_jsp(${main_jsp}) 값을 받아서 include 시킴 
		return "../main/main.jsp"; 
		// Controller(DispatcherServlet)는, JoinModel의 return 값을 보고 (DispatcherServlet에서 return값은 변수 jsp임), Model과 JSP를 연결시킨다.
		// Controller(DispatcherServlet)는, JoinModel의 return 값인 "../main/main.jsp"을 보고, 
		// redirect가 붙은게 아니라 파일명이니까, 해당 파일로 forward 시킨다.
		// 즉, Controller는 join.do를 url에 입력받으면 main.jsp 파일로 포워딩시킨다. 그래 그건 알겠는데 왜 ..이 붙는거지? 
		
		// 만약에 return "join.jsp"였으면 join.do로 forward 시킴 (실제 보여주는 UI는 join.jsp) 
		// 왜냐면 변수 jsp=join.jsp  ==> http://localhost/MVCProject4/member/join.do로 forward...
		// 근데 return이 "../main/main.jsp"니까 main.do로 forward 시키는거임. 
		// 왜냐면 변수 jsp=../main/main.jsp ==> http://localhost/MVCProject4/member/ + ../main/main.jsp  ==> main.do로 forward
	}

}
