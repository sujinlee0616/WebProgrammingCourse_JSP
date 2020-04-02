package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.*;
import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

// POJO 
@Controller // 메모리 할당해야하니까
public class BoardModel {
	
	@RequestMapping("board/list.do")
	public String boardListData(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("========== 순서파악: BoardModel - BoardListData ========== ");
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = BoardDAO.boardListData(map);
		int totalpage=BoardDAO.boardTotalPage();
		
		/* simpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 * Date date=new Date();
		 * String today=sdf.format(date);
		 * 이 코딩 3줄을 아래와 같이 한 줄로 줄일 수 있음 */
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		// JSP로 결과값 전송 
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);		
		request.setAttribute("today", today);
		
		return "list.jsp";
	}
	
	@RequestMapping("board/detail.do")
	public String boardDetailData(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("========== 흐름파악: BoardModel - boardDetailData ========== ");
		
		String no=request.getParameter("no");
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
		
		// detail.jsp로 전송
		request.setAttribute("vo", vo);
		
		return "detail.jsp";
	}
	
	@RequestMapping("board/insert_ok.do")
	public void boardInsert(HttpServletRequest request,HttpServletResponse response)
	{
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
			// response.sendRedirect("list.jsp"); // DispatcherServlet에서 redirect해주니까 이 소스 주석처리
		}catch(Exception ex){}
		
	}
	
	@RequestMapping("board/update.do")
	public void boardUpdate(HttpServletRequest request)
	{
		try
		{
			String no=request.getParameter("no");
			BoardVO vo=BoardDAO.boardUpdate(Integer.parseInt(no));
			
			// update.jsp로 전송
			request.setAttribute("vo", vo);
			
		}catch(Exception ex){}
	}
	
	@RequestMapping("board/update_ok.do")
	public void boardUpdateData(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
			String no=request.getParameter("no");
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
			// response.sendRedirect("detail.jsp?no="+no); // DispatcherServlet에서 redirect해주니까 이 소스 주석처리
		}catch(Exception ex){}
	}
}
