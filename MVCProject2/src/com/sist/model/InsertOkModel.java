package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class InsertOkModel implements Model {
	
	@Override
	public String execute(HttpServletRequest request){
		
		// JSPMiddleProject5 > BoardModel > boardInsert 메소드 복붙. sendRedirect 한 줄만 삭제.
		
		try
		{
			request.setCharacterEncoding("UTF-8");
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			// JSP와 달리 setProperty(*) 없음. ==> 하나씩 다 설정해줘야.
			
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO.boardInsert(vo);
			
		}catch(Exception ex){}
		
		return "redirect:list.do";
	}
}
