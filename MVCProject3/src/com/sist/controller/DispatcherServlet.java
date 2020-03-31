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
import javax.xml.parsers.*;
import org.w3c.dom.*;

// Controller는 어떤 Model과 어떤 JSP가 매칭되는지 알고 있어야 
// ==> MVCProject2에서는 배열로 설정해줬음 
// ==> MVCProject3에서는 파일(XML)로 설정해줬음  
//     Controller를 수정하려면 서버를 내려야하는데, XML 파일로 Model-JSP 관계를 설정해주면, XML만 수정하면 되므로 서버를 내리지 않고도 유지보수가 가능하다. 

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap();
    
	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation"); 
		// web.xml에서 <init-param>에서 value 받아옴 ==> path에 applicationContext.xml 지정됨 
		System.out.println("path="+path);
		try 
		{
			// XML Parser 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance(); // 객체 생성
			DocumentBuilder db=dbf.newDocumentBuilder(); 
			Document doc=db.parse(new File(path)); // File(String pathname): pathname에 해당되는 파일의 File 객체를 생성. 
			Element beans=doc.getDocumentElement();
			System.out.println("beans.getTagName()="+beans.getTagName());
			NodeList list = beans.getElementsByTagName("bean"); // applicationContext.xml의 <beans> 안에 <bean>을 모으겠다
			
			for(int i=0; i<list.getLength(); i++)
			{
				Element bean = (Element)list.item(i);
				System.out.println("bean.getTagName()="+bean.getTagName());
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("cls="+cls);
				
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance();
				System.out.println("id="+id);
				System.out.println("Model="+obj);
				clsMap.put(id, obj); // 위에서 만들어놓은 clsMap이라는 참조변수를 가지는 HashMap에 데이터 삽입 
				
			}
			
		} catch (Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI(); // cmd: command. 사용자가 입력한 명령. ex) list.do 
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
		}
		
	}
	
	// [참고] URI 
	// [참고] ? 및 ? 뒤의 parameter는 URI에 포함되지 않는다.
	//  - ex) http://localhost/MVCProject2/board.do?page=2
	//    ==> getURI 하면 ? 뒤는 포함하지 않음 

}







