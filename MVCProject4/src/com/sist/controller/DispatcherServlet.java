// DispatcherServlet은 Controller임. DispatcherServlet는 Spring에서 사용하는 controller 이름. 
package com.sist.controller;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
import com.sist.temp.Controller;

import javax.xml.parsers.*;
import org.w3c.dom.*;

// Controller는 어떤 Model과 어떤 JSP가 매칭되는지 알고 있어야 
// ==> MVCProject2에서는 배열로 설정해줬음 
// ==> MVCProject3에서는 파일(XML)로 설정해줬음  
//     Controller를 수정하려면 서버를 내려야하는데, XML 파일로 Model-JSP 관계를 설정해주면, XML만 수정하면 되므로 서버를 내리지 않고도 유지보수가 가능하다. 

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map clsMap = new HashMap();
    
	// 1. init 메소드에 (id,Model)을 가지는 map을 만들어놓는다.
	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation");  // getInitParameter(name:String): name에 해당하는 초기화 파라미터 값을 리턴
		// web.xml에서 <init-param>에서 value 받아옴 ==> path에 applicationContext.xml 지정됨 
		// System.out.println("path="+path); // path=C:\webDev\webStudy\MVCProject4\WebContent\WEB-INF\applicationContext.xml
		try 
		{
			// <XML Parser> 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance(); // 객체 생성
			DocumentBuilder db=dbf.newDocumentBuilder(); 
			Document doc=db.parse(new File(path)); // File(String pathname): pathname에 해당되는 파일의 File 객체를 생성. 
			Element beans=doc.getDocumentElement();
			//System.out.println("beans.getTagName()="+beans.getTagName());
			NodeList list = beans.getElementsByTagName("bean"); // applicationContext.xml의 <beans> 안에 <bean>을 모으겠다
			
			
			// <XML 파일을 읽어서 메모리 할당> 
			for(int i=0; i<list.getLength(); i++)
			{
				Element bean = (Element)list.item(i);
				//System.out.println("bean.getTagName()="+bean.getTagName());
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				//System.out.println("cls="+cls);

				// <메모리 할당>
				Class clsName=Class.forName(cls); // Class.forName(String className): Returns the Class object associated with the class or interface with the given string name.
				Object obj=clsName.newInstance(); 
				// 위의 두 줄은 A a = new A(); 와 똑같은 코딩임
				// Class 클래스: https://joont.tistory.com/165
				// Class 클래스 및 Reflection: https://joont.tistory.com/165
				
				
				// 2020.04.01 (수) 
				// <Annotation에 따라 동작하게 만들기> 
				// - Spring에서처럼 @Controller 어노테이션 없는 애는 Controller(DispatcherServlet)에서 등록되지 않게(Map에다가 put하지X) 해보자.  
				// - BoardModel, HomeModel, JoinModel, MainModel 이 4개에만 @Controller 붙여놨다. 
				//   ==> 이 4개만 갖고오는 걸 확인할 수 있다. (console에서 출력된 것 확인)
				// Controller con = (Controller)clsName.getAnnotation(Controller.class);
				if(clsName.isAnnotationPresent(Controller.class)==false) // isAnnotationPresent: 어노테이션이 있는지 확인 
					continue; // Controller annotation 없으면 continue해라
				
				System.out.println("id="+id);
				System.out.println("Model="+obj); 
				clsMap.put(id, obj); // 위에서 만들어놓은 clsMap이라는 참조변수를 가지는 HashMap에 데이터 삽입 
				
			}
			
		} catch (Exception ex) {}
	}


	// 2. 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI(); // cmd: command. 사용자가 입력한 명령. 
		System.out.println("cmd=request.getRequestURI()= "+cmd);
		cmd=cmd.substring(request.getContextPath().length()+1, cmd.lastIndexOf(".")); 
		// 예를 들어 http://localhost/MVCProject3/category.do 가 있다면, 거기서 category만 자른다. 
		
		// 사용자 요청 
		// 사용자 요청 처리 ==> Model을 찾는다. 
		Model model=(Model)clsMap.get(cmd);
		
		// 요청 처리 
		String jsp=model.handlerRequest(request); // request에 값을 싣어서 String jsp에 보내겠다.
		
		// 화면이동(SendRedirect) 또는 request 전송(forward)
		//  - Model에서 'return 파일명' ==> forward
		//  - Model에서 'return redirect:list.do' 이런 애들 ==> SendRedirect
		if(jsp.startsWith("redirect"))
		{
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
		}
		else 
		{
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			System.out.println("jsp="+jsp);
			
		}
		
	}
	
	// [참고] URI 
	// [참고] ? 및 ? 뒤의 parameter는 URI에 포함되지 않는다.
	//  - ex) http://localhost/MVCProject2/board.do?page=2
	//    ==> getURI 하면 ? 뒤는 포함하지 않음 

}

/*
 * 2020.04.01(수)
 * <Servlet>
 * 
 * 1. init() 메소드
 *  - 서블릿 컨테이너는 서블릿 객체가 생성된 후, 단 한번 init() 메소드를 호출한다.
 *  - 서블릿은 init() 메소드가 완료되어야 서비스할 수 있다.
 * 
 * 2. service() 메소드
 *  - 클라이언트가 서블릿에 요청을 보낼때마다, 서블릿 컨테이너는 서블릿의 service() 메소드를 호출한다.
 *  - 전달받은 ServletRequest 타입의 객체를 통해서 요청정보와 클라이언트가 전달한 데이터를 읽을 수 있으며, 
 *    전달받은 ServletResponse 타입의 객체를 사용하여 클라이언트에게 응답할 수 있다.
 * 
 * 3. destroy() 메소드
 *  - 서블릿이 더이상 서비스를 하지 말아야 할 때 서블릿 컨테이너에 의해 호출된다.
 *  - 이 메소드는 프로그래머가 호출하는게 아니다.
 *  - 따라서 destroy() 를 예제로 확인하려면 "톰캣 매니저"를 사용하여 애플리케이션을 언로드하거나 톰캣을 셧다운시켜야 한다.
 * 
 */

/*
 * 2020.04.01(수)
 * <Annotation>
 *  - Annotation 생성 시, 선택한 Target에 따라 어노테이션 쓰는 위치가 다르다.
 *    (Target 선택: New > Annotation > @Target에서 체크박스에 원하는 것 체크) 
 * 
 * @ => TYPE, 클래스 구분 (ex. 이 클래스에 메모리 할당을 할건지 안 할건지)  
 * 		===> @Controller, @Repository, @Component, @Service 
 * 			 Model        DAO          일반클래스          Manager(외부에서 데이터를 갖고 온다던가... 등등)													
 * public class A
 * {
 * 		@ => FIELD (메모리 주소 전송) ===> @Autowired(자동 메모리 할당) 
 * 		private B b;
 * 
 * 		public void setB(B b) => PARAMETER ===> @Resource 
 * 		{
 * 			this.b=b;
 * 		}
 * 
 * 		@ => CONSTRUCTOR (생성자 제어 시)  
 * 		public A()
 * 		{
 * 		}
 * 
 * 		@ => METHOD 
 * 		public void display()
 * 		{
 * 		} 
 * }
 * 
 * 
 */


/*
 *  
 * public class A
 * {
 * 		public void aaa(String a, int b){}
 * 		public void bbb(String a){}
 * 		public void ccc(String a, double d){}
 * }
 * 
 *  ==>  a=>aaa, b=>bbb, c=>ccc
 *  => char input
 *  
 *  A aa=new A();
 *  if(input=='a')
 *  	aa.aaa("",10);
 *  else if(input=='b')
 *  	aa.bbb("");
 *  else if(input=='c')
 *  	aa.ccc("",10.5); 
 * 
 * ===> 요렇게 코딩하면 참 번거롭다. 특히, 협업 시에 어려움이 예상됨.
 *  ===> @Autowired 쓰면 자동 메모리 할당. 얼마나 편하니. 
 * 
 */




