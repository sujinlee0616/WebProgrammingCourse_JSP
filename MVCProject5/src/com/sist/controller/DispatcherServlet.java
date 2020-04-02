// Controller
package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> list = new ArrayList<String>();
	
	// [init 메소드] - 메소드 실행시점: 최초로 서블릿 요청이 들어올 때, 실행횟수: 1, 기능: 초기화 작업
	public void init(ServletConfig config) throws ServletException { 
		System.out.println("========== 흐름파악: DispatcherServlet - init 메소드 (시작) ========== ");
		// 1. web.xml의 path를 읽는다
		String path=config.getInitParameter("contextConfigLocation"); // web.xml에서 이름이 contextConfigLocation인 init-param의 value를 갖고 온다. 
		System.out.println("path="+path);
		// app.xml에는 클래스 메모리 할당해야 하는 클래스가 올라가야 한다.
		String defaultPath=config.getInitParameter("defaultPath"); // web.xml에서 이름이 defaultPath인 init-param의 value를 갖고 온다.
		System.out.println("defaultPath="+defaultPath);
		
		// 2. HandlerMapping한테 web.xml path 넘겨줌 
		// ==> HandlerMapping은 
		//     1) XML Parser 써서 SAX parsing해서 web.xml 파싱해서 pList에 패키지 명을 담는다
		//     2) ComponentScan 써서 클래스명을 가져와서 list에 클래스명(파일명) 담는다.
		HandlerMapping hm=new HandlerMapping(path,defaultPath); 
		list=hm.getList(); // 파일명 가져옴 
		System.out.println("init의 list="+list);
		System.out.println("========== 흐름파악: DispatcherServlet - init 메소드 (끝) ========== ");
	}

	
	// [service 메소드] - 메소드 실행시점: 클라이언트로부터 요청이 있을 때마다 실행, 실행횟수: n, 기능: 실제 서블릿이 처리해야 하는 작업
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("========== 흐름파악: DispatcherServlet - service 메소드 (시작) ========== ");
		String cmd=request.getRequestURI();
		System.out.println("cmd="+cmd);
		cmd=cmd.substring(request.getContextPath().length()+1);
		System.out.println("cmd="+cmd);  // cmd= main/main.do 이렇게..
		
		try 
		{
			// 메모리 할당 
			for(String cls:list)
			{
				Class clsName=Class.forName(cls); 
				
				// @Controller 어노테이션 안 붙은 애들에게는 메모리 할당 안 해주려고 continue 써서 continue 아래의 메모리 할당 코드 수행하지 않도록 처리
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue; 				
				Object obj =clsName.newInstance(); // 메모리 할당
				System.out.println("obj="+obj);
				
				// 메소드를 찾아서 호출
				Method[] methods=clsName.getDeclaredMethods(); // clsName 클래스 안의 모든 메소드를 가져와라 (Reflection)
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					/* 
					 * @RequestMapping("main/list.do")
					 * 리스트메소드
					 *
					 * ==> 리스트메소드가 @RequestMapping("main/list.do")을 갖고 있는 것
					 *     즉, 메소드m이 @RequestMapping 어노테이션을 갖고 있음
					 */
					if(cmd.equals(rm.value()))
					{
						String jsp=(String)m.invoke(obj, request, response); // Object를 String으로 형변환
						System.out.println("jsp="+jsp);
						
						if(jsp.startsWith("redirect")) // ex) return "redirect:list.do" 
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
							// sendRedirect: 이전 request를 유지할 필요 없는 애들.
							// ==> request.setAttribute 없는 애들.  ex) xxxModel.java
						}
						else // ex) return "list.jsp"
						{
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
							// forward: 이전 request를 유지해야 하는 애들. 
							// ==> request.setAttribute로 리퀘스트에 값 싣어준 애들. ex) xxxModel.java
						}
						
						return; // 메소드 종료 
					}
				}
			}
		} catch (Exception ex) {}
		System.out.println("========== 흐름파악: DispatcherServlet - service 메소드 (끝) ========== ");
	}

	
	// [destroy 메소드] - 메소드 실행시점: 서블릿 객체가 메모리에서 삭제될 때, 실행횟수: 1, 기능: 자원 해제
	
}









