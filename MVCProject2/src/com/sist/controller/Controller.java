package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
import java.util.*;

public class Controller extends HttpServlet {
	// Spring: 아래와 같이 XML에 class 등록한다. 
	// <bean id="list" class="com.sist.model.ListModel"/> ==> 이렇게 map 에 저장 
	
	// Controller는 어떤 Model과 어떤 JSP가 매칭되는지 알고 있어야 
	// ==> 이번 프로젝트(MVCProject2)에서는 배열로 설정해줬음 
	// ==> MVCProject3에서는 파일(XML)로 설정해줬음 
	private static final long serialVersionUID = 1L;
	private String[] strCmd={"list","insert","insert_ok","detail","update","update_ok","movielist"}; // 얘가 map에 들어가는 key // 클래스 이름만 넘기면 메모리 할당 가능...
	private String[] strCls={
			"com.sist.model.ListModel",
			"com.sist.model.InsertModel",
			"com.sist.model.InsertOkModel",
			"com.sist.model.DetailModel",
			"com.sist.model.UpdateModel",
			"com.sist.model.UpdateOkModel",
			"com.sist.model.MovieListModel"
	};
	private Map clsMap=new HashMap();
	/*
	 * map.put("list", new ListModel())
	 * 				   ============== 주소값이 들어감. (ex. 100번지..)
	 * [메모리 할당]
	 * 	Controller con = new Controller();
	 *  con.init()
	 *  {
	 *  	초기화 ==> 생성자 
	 *  	// init은 한 번만 호출됨 
	 *  }
	 *  con.service()
	 *  {
	 *  	// service는 여러번 호출가능
	 *  }
	 *  con.destroy()
	 *  {
	 *  	System.gc(); // 메모리 해제 
	 *  }
	 */
	
	public void init(ServletConfig config) throws ServletException {
		try
		{
			for(int i=0;i<strCls.length;i++)
			{
				Class clsName=Class.forName(strCls[i]);
				Object obj=clsName.newInstance(); // 메모리 할당
				System.out.println(obj);
				clsMap.put(strCmd[i], obj);
			}
		}catch(Exception ex){}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		// URI: http://localhost/MVCProject2/list.do 에서 "/MVCProject2/list.do" 이 부분 
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
		Model model=(Model)clsMap.get(cmd);
		// System.out.println(model);
		String jsp=model.execute(request);
		// System.out.println(jsp);
		if(jsp.startsWith("redirect"))  // Model에서 return "redirect:list.do"; 이런 애들은 sendRedirect 시킴 
		{
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
			// ex) UpdateOkModel.java 보면 return "redirect:detail.do"; ==> detail.do 로 리다이렉트 시킴 
		}
		else  // Model에서 return "board/detail.jsp"; 이런 애들은 forward 됨 
		{
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
		
		// 실행 => url 마지막 부분을 list.do로 변경 
		// ==> com.sist.model.ListModel@6a68a118, board/list.jsp(리턴값)을 출력 
	}

}
