package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;

// MVC: Controller ==> Model ==> Controller ==> View
// ====> Controller를 실행해야 데이터가 제대로 들어간 화면이 출력됨. 
//      (JSP파일을 실행하면 데이터가 안 들어간 와꾸만 나옴.)
//      즉, 이제부터는 JSP 파일이 아니라 Controller 파일 실행해야.

// 이런식으로...
// Controller=?cmd=list
// Controller=?cmd=detail
// Controller=?cmd=insert

@WebServlet("*.do") // ~~.do ==> Controller를 호출한다. 
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String cmd=request.getParameter("cmd");
		String uri=request.getRequestURI();
		// http://localhost/MVCProject1/list.do 에서
			// URI: /MVCProject1/list.do
			// ContextPath: /MVCProject1/
		
		// 요청받기 => 처리 => Model 호출  
		
		// ex) URL=http://localhost/MVCProject1/member/list.do 입력 
		String cmd=uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".")); 
		// System.out.println(cmd); // 결과: list 
		String jsp=uri.substring(request.getContextPath().length(),uri.lastIndexOf("."));
		// System.out.println(jsp); // 결과: /member/list
		jsp=jsp+".jsp";
		// System.out.println(jsp); // 결과: /member/list.jsp
		
		if(cmd.equals("list"))
		{
			ListModel model=new ListModel();
			model.execute(request);
		}
		else if(cmd.equals("detail"))
		{
			DetailModel model=new DetailModel();
			model.execute(request);
		}
		else if(cmd.equals("insert"))
		{
			InsertModel model=new InsertModel();
			model.execute(request);
		}
		else if(cmd.equals("update"))
		{
			UpdateModel model=new UpdateModel();
			model.execute(request);
		}
		// if ~else if 문이 너무 길다고 생각하지 않나?
		// ==> map 쓰면 됨
		// 	   map.put("list", new ListModel()); 이런식으로 
		// ==> MVC Project2의 Controller 참고 
		
		
		
		// 이 JSP 파일에
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		// request를 보내라
		rd.forward(request, response);
		
		// [참고] RequestDispatcher
		// RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 
		// 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스입니다. 
		// 즉 /a.jsp 로 들어온 요청을 /a.jsp 내에서 RequestDispatcher를 사용하여 b.jsp로 요청을 보낼 수 있습니다. 
		// 또는 a.jsp에서 b.jsp로 처리를 요청하고 b.jsp에서 처리한 결과 내용을 a.jsp의 결과에 포함시킬 수 있습니다.
		
		// [참고] 응집성, 결합성 
		// 응집성: 하나의 클래스에 관련 기능을 다 모으란 것. 하나의 클래스에 여러개의 메소드로. 
		// 결합성: A가 B에 얼마나 영향을 미치는가
		// 응집성은 높고, 결합성은 낮게 짜야. 
		
	}

}







