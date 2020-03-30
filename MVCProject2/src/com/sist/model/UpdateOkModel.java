package com.sist.model;

import javax.servlet.http.HttpServletRequest;
/*
 *	Model => execute()
 *
 *  class ListModel implements Model
 *  {
 *  	execute(){}
 *  	list(){}
 *  }
 *  
 * 
 */

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class UpdateOkModel implements Model {
	
	@Override
	public String execute(HttpServletRequest request){
		
		// JSPMiddleProject5 > BoardModel > boardUpdateData 메소드 복붙. String no를 밖으로 뺐음. 
		
		String no="";
		
		try
		{
			request.setCharacterEncoding("UTF-8");
			no=request.getParameter("no");
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			// JSP와 달리 setProperty(*) 없음. ==> 하나씩 다 설정해줘야.
			
			BoardVO vo=new BoardVO();
			vo.setNo(Integer.parseInt(no));
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO.boardUpdateData(vo);
			/*response.sendRedirect("list.jsp");*/
			
		}catch(Exception ex){}
		
		
		return "redirect:detail.do?no="+no;
	}

}
